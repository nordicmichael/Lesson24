package com.example.lesson24

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson24.databinding.FragmentFirstBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class FirstFragment : Fragment() {

    private var binding: FragmentFirstBinding? = null
    private var textList = listOf("Hey!", "My name is Bot", "I'm your virtual assistant",
        "Do you have any questions?)")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        CoroutineScope(Dispatchers.IO).launch {

            delay(1500)

            var posts = listOf("")

//            thread {
//                posts = getPostsFromServer()
//            }
//
//            liveData {
//                binding?.tvSomeText?.text = posts.toString()
//            }

            withContext(Dispatchers.Main) {
                for (i in 0.. textList.size - 1) {

                    changeText(i)
                }
            }
        }

//        Observable.just(textList[0], textList[1], textList[2], textList[3])
//            .subscribe(getObserver())

        Observable.fromIterable(textList.asIterable()).delay(3, TimeUnit.SECONDS).subscribe(getObserver())
    }

    private fun getObserver(): Observer<String> {

        return object : Observer<String> {

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

            override fun onNext(text: String) {
                //Handler(Looper.getMainLooper()).postDelayed({

                    binding?.tvSecondText?.text = text
               // }, 3000)
            }
        }
    }

    private fun generateSleepTime(): Long {

        return (1000..7000).random().toLong()
    }

    private suspend fun returnText(position: Int): String {

//        for (i in 0..10) {
//
//            Log.e("My text", "Hello, World!")
        //        }
        delay(3000)

        return textList[position]
    }

    private suspend fun changeText(position: Int) {

        binding?.tvFirstText?.text = returnText(position)
    }

    private fun getPostsFromServer(): List<String> {

        return listOf("")
    }
}