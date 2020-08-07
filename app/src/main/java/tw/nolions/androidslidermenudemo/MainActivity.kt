package tw.nolions.androidslidermenudemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        slideMenu.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->

            text.text = when (menuItem.itemId) {
                R.id.sidleMenuHome -> getString(R.string.home)
                R.id.sidleMenuAccount -> getString(R.string.account)
                R.id.sidleMenuEmail -> getString(R.string.email)
                R.id.sidleMenuMore -> getString(R.string.more)
                else -> "other"
            }

            true
        })
    }
}