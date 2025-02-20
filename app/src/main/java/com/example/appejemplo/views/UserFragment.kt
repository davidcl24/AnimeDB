package com.example.appejemplo.views

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.example.appejemplo.databinding.FragmentUserBinding
import com.example.appejemplo.utils.CameraUtils
import com.example.appejemplo.viewmodels.UserViewModel

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private lateinit var cameraUtils: CameraUtils
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var takePicLauncher: ActivityResultLauncher<Uri>
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cameraUtils = CameraUtils(requireContext(), requireActivity().contentResolver)

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val allPermissionsGranted = permissions.all { it.value }
            if (allPermissionsGranted) {
                Toast.makeText(requireContext(), "Todos los permisos concedidos", Toast.LENGTH_SHORT).show()
                cameraUtils.takePicture(takePicLauncher)
            } else {
                val rationaleRequired = permissions.keys.any {permission ->
                    ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission)
                }
                if (rationaleRequired) {
                    Toast.makeText(requireContext(), "Se necesitan permisos para continuar", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Habilita los permisos desde los ajustes de android", Toast.LENGTH_SHORT).show()
                }
            }
        }

        takePicLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                viewModel.updatePhotoUri(cameraUtils.getPhotoUri())
            }
        }

        viewModel.state.observe(requireActivity()) { state ->
            state?.let { value ->
                binding.imageView2.setImageURI(value.photoUri)
            }
        }

        binding.button.setOnClickListener {
            val permissionNeeded = cameraUtils.getCameraPermission(requireContext())
            if (permissionNeeded.isNotEmpty()) {
                requestPermissionLauncher.launch(permissionNeeded.toTypedArray())
            }

        }

    }

}