package nadirian.hamlet.android.encdecapp.fragments.data_encryption_standard_fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nadirian.hamlet.android.encdecapp.R
import nadirian.hamlet.android.encdecapp.databinding.FragmentDESCipherDecBinding
import nadirian.hamlet.android.encdecapp.model.data_encryption_standard.DESEncryption.decrypt

class DESCipherDecFragment : Fragment() {

    private var _binding:FragmentDESCipherDecBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDESCipherDecBinding.inflate(inflater,container,false)
        val view = _binding!!.root
        var ciphertextForDecEdt = binding!!.ciphertextForDecEdt.text
        binding!!.sizeKeyForDecTv.text = 0.toString()
        binding!!.keyForDecEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                var keyLength = binding!!.keyForDecEdt.length()
                var convertToIntSize = (keyLength.toInt()*8)
                if (convertToIntSize==64){
                    binding!!.sizeKeyForDecTv.setTextColor(Color.GREEN);
                }
                else{
                    binding!!.sizeKeyForDecTv.setTextColor(Color.RED);
                }
                binding!!.sizeKeyForDecTv.text=convertToIntSize.toString()

            }
        })
        binding!!.initVectorForDecEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                var keyLength = binding!!.initVectorForDecEdt.length()
                var convertToIntSize = (keyLength.toInt()*8)
                if (convertToIntSize==64){
                    binding!!.sizeIVForDecTv.setTextColor(Color.GREEN);
                }
                else{
                    binding!!.sizeIVForDecTv.setTextColor(Color.RED);
                }
                binding!!.sizeIVForDecTv.text=convertToIntSize.toString()
            }
        })

        binding!!.clearForDecBt.setOnClickListener {
            binding!!.keyForDecEdt.setText("")
            binding!!.initVectorForDecEdt.setText("")

        }
        var secretKey= binding!!.keyForDecEdt.text
        var IV=binding!!.initVectorForDecEdt.text
        /*    binding!!.keyForDecEdt.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    binding!!.plaintextForDecEdt.setText(decrypt(secretKey.toString(),IV.toString(),ciphertextForDecEdt.toString()))
                    val imm =
                        v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                    true
                }
                false
            }*/
        binding!!.decryptBtn.setOnClickListener {
            binding!!.plaintextForDecEdt.setText(decrypt(secretKey.toString(),IV.toString(),ciphertextForDecEdt.toString()))
        }
        return view
        // Inflate the layout for this fragment
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}