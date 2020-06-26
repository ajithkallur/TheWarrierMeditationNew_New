package com.example.thewarriermeditationnew_new.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.thewarriermeditationnew_new.R
import com.example.thewarriermeditationnew_new.ui.notifications.NotificationsFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {
    private var dashboardViewModel: DashboardViewModel? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var button: Button
    val TAG = "MyMessage"
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        auth = Firebase.auth
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signup.setOnClickListener() {
            auth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            Toast.makeText(getActivity(),"User sign up successful.",Toast.LENGTH_SHORT).show()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            //Toast.makeText(applicationContext,"this is toast message",Toast.LENGTH_SHORT).show()
                            Toast.makeText(getActivity(),"User sign up failed",Toast.LENGTH_SHORT).show()
                        }

                    }
        }
        login.setOnClickListener() {
            auth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                           // updateUI(user)
                            Toast.makeText(getActivity(),"Authentication success",Toast.LENGTH_SHORT).show()
                            //loadMemberFragment()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(getActivity(),"Authentication failed",Toast.LENGTH_SHORT).show()
                          //  Toast.makeText(baseContext, "Authentication failed.",
                             //       Toast.LENGTH_SHORT).show()
                           // updateUI(null)
                            // ...
                        }

                        // ...
                    }
        }
    }
  fun  loadMemberFragment(){

      val notificationFragment: Fragment = NotificationsFragment()
      val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
      transaction.replace(R.id.fragment_container_view_tag, notificationFragment) // give your fragment container id in first parameter

      transaction.addToBackStack(null) // if written, this transaction will be added to backstack
      transaction.commit()
  }

}




