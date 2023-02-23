package com.karine.midterm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.karine.midterm.databinding.FragmentFirstBinding
import java.util.*
import kotlin.collections.ArrayDeque

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            if (binding.editTextNumber.text.isBlank()) {
                Log.e("Tower of Hanoi", "No input")
            } else {
                var numberOfNodes = binding.editTextNumber.text.toString().toInt();

                towerOfHanoi(numberOfNodes);
            }
        }
    }


    fun towerOfHanoi(n: Int) {
        Log.i("Tower of Hanoi", "Starting move for input ${n}")
        var rod1 = Rod("Rod 1")
        var rod2 = Rod("Rod 2")
        var rod3 = Rod("Rod 3")
        initSource(rod1, n)
        fun moveNodes(n: Int, source: Rod, target: Rod, extra: Rod) {
            if (n == 0) {
                Log.w("Tower of Hanoi", "No nodes where present")
            }
            if (n == 1) {
                moveLast(source, target)
                return
            }
            moveNodes(n - 1, source, extra, target)
            moveNodes(1, source, target, extra)
            moveNodes(n - 1, extra, target, source)
        }
        moveNodes(n, rod1, rod3, rod2)
    }

    private fun initSource(source: Rod, n: Int) {
        for (i in n downTo 1) {
            source.push(i)
        }
    }

    fun moveLast(source: Rod, target: Rod) {
        var element = source.pop();
        Log.i(
            "Tower of Hanoi",
            "Moving element of size ${element} from ${source.name} to ${target.name}"
        )
        target.push(element);
    }

    class Rod(pName: String) {
        var name: String = pName
        var stack = ArrayDeque<Int>()

        fun push(node: Int) {
            if (stack.isEmpty() || node < stack.last()) {
                stack.addLast(node)
            }
        }

        fun pop(): Int {
            if (!stack.isEmpty()) {
                return stack.removeLast();
            }
            return 0;
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}