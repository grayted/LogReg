package com.example.veggieneighbors



import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NearmeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NearmeFragment : Fragment(), OnMapReadyCallback  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mapView: MapView? = null
    private lateinit var mMap: GoogleMap

    private val a = LatLng(37.6260748, 127.07979149)
    private val b = LatLng(37.62602666, 127.07872522)

    private var locationArrayList: ArrayList<LatLng>? = null
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nearme, container, false)

        // Initialize the MapView
        mapView = view.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)

        locationArrayList = ArrayList()
        locationArrayList!!.add(a)
        locationArrayList!!.add(b)

        return view
    }

    //==>
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        for (i in locationArrayList!!.indices) {
            mMap.addMarker(MarkerOptions().position(locationArrayList!![i]).title("Marker"))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList!!.get(i)))
        }

        val seoul = LatLng(37.6271512, 127.0791017)
        val marker = MarkerOptions().position(seoul).title("Marker in Seoul")
        mMap.addMarker(marker)

        val cameraOption = CameraPosition.Builder().target(seoul).zoom(17f).build()
        val camera = CameraUpdateFactory.newCameraPosition(cameraOption)
        mMap.moveCamera(camera)

        /*
        mMap.setOnMarkerClickListener { clickedMarker ->
            // Handle marker click event (e.g., navigate to a new activity)
            // Note: Replace NextActivity::class.java with your desired activity

            val intent = Intent(requireContext(), NextActivity::class.java)
            startActivity(intent)
            true
        }
        
         */
    }

    //<==
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NearmeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NearmeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}