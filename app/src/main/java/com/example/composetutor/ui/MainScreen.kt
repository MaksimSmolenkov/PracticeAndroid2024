import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.composetutor.R

@Composable
fun MainScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.marvel_logo),
            contentDescription = "Marvel Logo",
            modifier = Modifier
                .height(100.dp)
                .padding(bottom =  16.dp)
        )
        Text(
            text = "Choose your hero",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        HeroList(navController)
    }
}

@Composable
fun HeroList(navController: NavController) {
    val heroes = listOf(
        Hero("Spider-Man", "https://s3-alpha-sig.figma.com/img/3afa/826d/91d576800cc61a71d74ddcc6fbc7420d?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=SKFQ2i5Yngl9qejWI69V5LJqtmBU7b2gd~9Hsp9GWjkmX3Tq1XtPw2fWQrUyWaOFXuBQkTA~XHIxjDNbO-kU9fuE6anmHoZ348dcyrfFeGAg3ZNx0vlpY~paB9ZCcXN95KfdAcMvX~hejHCZMEIh0bOoAZZpeHGKvTelV49ZevFA0lG6nMtxjHm-n7ncYK2AFGhS2m0ALWvhTESuACAzqoXzeK8kVn3xOhWUG50SkzpQAfv1tQnjpANsnsFC8uNmEiIsMtMbmWIsGl4Fg~yHrQNJaJFzUcaGvCqF1cRTbKfMdwsm7kibxpaGtHXUu5p7IdJPOplaP6Vl7id73MRayQ__", "Peter Parker became Spider-Man..."),
        Hero("Iron Man", "https://s3-alpha-sig.figma.com/img/d6ff/6e53/06e9a778c50e17ebd04b812b3a8258ef?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=mQfkAV4MhIM-hTmFL5YaDWb5GhcHAZHWJMGtaLjWdtTQBr6IejjDWDkaKuXVKt3e74db0WIfWyqwXLo7flHlQY3i2~xihvnZqxckjdhK54720JuTGroV7TkGFriI0v763~au5xooebGlpEQ~Q17e8ZrrB-cH7hAJHqlQSYGmGMUR7c6tfIpAcf15yUxyGEjcMlr3-KX1G1kXXCmWvkFUCK2L3Oqx6DjyYTNgRn8J~Pe0-2O8tNtg7BKyfC5Rl0laqgTbIMFyvqAg50l~--LS9kdle6EjiU0d6kky58AM~wns4WBUhNgAujrGWX2jwV4sVjLKm4alSpZZU6J1btlriw__", "Tony Stark created the Iron Man suit..."),
        Hero("Deadpool", "https://s3-alpha-sig.figma.com/img/fe6f/5401/a1fcbba62871e9372ad6ba08bc49f429?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=WoWyh69Ng3BElspM96rgIfoZ86cWOBZNwFIGOIyAJztujgWR4yjMiL4KBCKA2CVoIkKIUZgMf1B1sAL~wZ-bfvBRWgRIAKjetkAdsWLuA12snLLkUwPRTDG7TRTqz3Tc-yvzWQbGl6dp3Kh-nXszmgy~38-n1NsXLxzJ0ipaY3mkRb3x48jLB42CCvy3VIzM8byHyHvZxhzKLAkKZugNkoQmJ9U2OBS3N6XPSoVa06cJZG-eceM4Y1BxUTsi1l407VpjJZGpk0OQXN-nVq~1veqbD95gHUKe5HRuvMNUAI9LZxfXxYgO9YKNdzg-h4tCsdNJ8bUCANDu6sA3Iar7OA__", "Deadpool ..."),
        )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(heroes) { hero ->
            HeroItem(hero = hero, onClick = {
                navController.navigate("detail/${hero.name}")
            })
        }
    }
}

@Composable
fun HeroItem(hero: Hero, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .height(200.dp)
            .width(150.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = hero.image),
            contentDescription = hero.name,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .shadow(1.dp)
        )
        Text(text = hero.name, style = MaterialTheme.typography.h6)
    }
}

data class Hero(val name: String, val image: String, val description: String)
