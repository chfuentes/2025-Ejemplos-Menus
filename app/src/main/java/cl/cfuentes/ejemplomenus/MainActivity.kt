package cl.cfuentes.ejemplomenus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import cl.cfuentes.ejemplomenus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.bnMenu.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.mi_inicio -> abrirFragmento(InicioFragment())
                R.id.mi_explorar -> abrirFragmento(ExplorarFragment())
            }
            true
        }

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            binding.dlLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.dlLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.nvLateral.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ml_inicio -> abrirFragmento(InicioFragment())
                R.id.ml_quienes -> abrirFragmento(QuienesSomosFragment())
            }
            binding.dlLayout.closeDrawer(GravityCompat.START)
            true
        }

        //Inicializar UI
        abrirFragmento(InicioFragment())
    }

    private fun abrirFragmento(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.flContainer.id,fragment)
            .commit()
    }
}