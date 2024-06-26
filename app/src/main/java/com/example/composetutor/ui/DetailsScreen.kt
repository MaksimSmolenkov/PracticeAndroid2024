import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun DetailScreen(navController: NavController, backStackEntry: NavBackStackEntry) {
    val heroName = backStackEntry.arguments?.getString("heroName") ?: ""
    val hero = getHeroByName(heroName)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = rememberImagePainter(data = hero.image),
            contentDescription = hero.name,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = hero.name, style = MaterialTheme.typography.h4, modifier = Modifier.padding(bottom = 8.dp))
        Text(text = hero.description, style = MaterialTheme.typography.body1)
    }
}

fun getHeroByName(name: String): Hero {
    return when (name) {
        "Spider-Man" -> Hero("Spider-Man", "https://s3-alpha-sig.figma.com/img/3afa/826d/91d576800cc61a71d74ddcc6fbc7420d?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=SKFQ2i5Yngl9qejWI69V5LJqtmBU7b2gd~9Hsp9GWjkmX3Tq1XtPw2fWQrUyWaOFXuBQkTA~XHIxjDNbO-kU9fuE6anmHoZ348dcyrfFeGAg3ZNx0vlpY~paB9ZCcXN95KfdAcMvX~hejHCZMEIh0bOoAZZpeHGKvTelV49ZevFA0lG6nMtxjHm-n7ncYK2AFGhS2m0ALWvhTESuACAzqoXzeK8kVn3xOhWUG50SkzpQAfv1tQnjpANsnsFC8uNmEiIsMtMbmWIsGl4Fg~yHrQNJaJFzUcaGvCqF1cRTbKfMdwsm7kibxpaGtHXUu5p7IdJPOplaP6Vl7id73MRayQ__", "Peter Parker became Spider-Man...")
        "Iron Man" -> Hero("Iron Man", "https://s3-alpha-sig.figma.com/img/d6ff/6e53/06e9a778c50e17ebd04b812b3a8258ef?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=mQfkAV4MhIM-hTmFL5YaDWb5GhcHAZHWJMGtaLjWdtTQBr6IejjDWDkaKuXVKt3e74db0WIfWyqwXLo7flHlQY3i2~xihvnZqxckjdhK54720JuTGroV7TkGFriI0v763~au5xooebGlpEQ~Q17e8ZrrB-cH7hAJHqlQSYGmGMUR7c6tfIpAcf15yUxyGEjcMlr3-KX1G1kXXCmWvkFUCK2L3Oqx6DjyYTNgRn8J~Pe0-2O8tNtg7BKyfC5Rl0laqgTbIMFyvqAg50l~--LS9kdle6EjiU0d6kky58AM~wns4WBUhNgAujrGWX2jwV4sVjLKm4alSpZZU6J1btlriw__", "Tony Stark created the Iron Man suit...")
        "Deadpool" -> Hero("Deadpool", "https://s3-alpha-sig.figma.com/img/fe6f/5401/a1fcbba62871e9372ad6ba08bc49f429?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=WoWyh69Ng3BElspM96rgIfoZ86cWOBZNwFIGOIyAJztujgWR4yjMiL4KBCKA2CVoIkKIUZgMf1B1sAL~wZ-bfvBRWgRIAKjetkAdsWLuA12snLLkUwPRTDG7TRTqz3Tc-yvzWQbGl6dp3Kh-nXszmgy~38-n1NsXLxzJ0ipaY3mkRb3x48jLB42CCvy3VIzM8byHyHvZxhzKLAkKZugNkoQmJ9U2OBS3N6XPSoVa06cJZG-eceM4Y1BxUTsi1l407VpjJZGpk0OQXN-nVq~1veqbD95gHUKe5HRuvMNUAI9LZxfXxYgO9YKNdzg-h4tCsdNJ8bUCANDu6sA3Iar7OA__", "Deadpool ...")
        else -> Hero("Unknown", "", "No description available.")
    }
}
