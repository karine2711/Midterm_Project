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
            var numberOfNodes = binding.editTextNumber.text.toString().toInt();
            towerOfHanoi(numberOfNodes);
        }
    }


    fun towerOfHanoi(n: Int) {
        var rod1 = Rod("Rod 1")
        var rod2 = Rod("Rod 2")
        var rod3 = Rod("Rod 3")
        var rods: List<Rod> = mutableListOf(rod1, rod2, rod3);
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
    }

    fun moveLast(source: Rod, target: Rod) {
        Log.i("Tower of Hanoi", "Moving from ${source.name} to ${target.name}")
        target.push(source.pop());
    }

    class Rod(pName: String) {
        var name: String = pName
        var stack = ArrayDeque<Int>()

        fun push(node: Int) {
//            Log.i(name, "Pushing ${node}to $name")
            if (node < stack.last()) {
                stack.addLast(node)
            }
        }

        fun pop(): Int {
            return stack.removeLast();
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}