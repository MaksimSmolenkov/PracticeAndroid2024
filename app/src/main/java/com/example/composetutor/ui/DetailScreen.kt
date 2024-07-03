import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

@Composable
fun DetailScreen(navController: NavController, backStackEntry: NavBackStackEntry) {
    val heroName = backStackEntry.arguments?.getString("heroName") ?: ""
    val hero = getHeroByName(heroName)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = hero.image),
            contentDescription = hero.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = hero.name,
                style = MaterialTheme.typography.h3.copy(
                    color = Color.White,
                    fontSize = 32.sp
                ),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .background(color = Color.Black.copy(alpha = 0.3f))

            )
            Text(
                text = hero.description,
                style = MaterialTheme.typography.body1.copy(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = Color.Black.copy(alpha = 0.3f))

            )
        }
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .background(color = Color.Black.copy(alpha = 0.5f), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}

fun getHeroByName(name: String): Hero {
    return when (name) {
        "Spider-Man" -> Hero(
            "Spider-Man",
            "https://images.wallpapersden.com/image/download/spider-man-ps4_a2dubmuUmZqaraWkpJRmZW1lrWdpZWU.jpg",
            "Peter Parker became Spider-Man when he was bitten by radioactive spider.Peter struggles to help his widowed Aunt May pay the rent, is taunted by Flash, and continues fighting crime and saving the city as Spider-Man",
            Color(0xFFFF000C))
        "Iron Man" -> Hero(
            "Iron Man",
            "https://s3-alpha-sig.figma.com/img/d6ff/6e53/06e9a778c50e17ebd04b812b3a8258ef?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=mQfkAV4MhIM-hTmFL5YaDWb5GhcHAZHWJMGtaLjWdtTQBr6IejjDWDkaKuXVKt3e74db0WIfWyqwXLo7flHlQY3i2~xihvnZqxckjdhK54720JuTGroV7TkGFriI0v763~au5xooebGlpEQ~Q17e8ZrrB-cH7hAJHqlQSYGmGMUR7c6tfIpAcf15yUxyGEjcMlr3-KX1G1kXXCmWvkFUCK2L3Oqx6DjyYTNgRn8J~Pe0-2O8tNtg7BKyfC5Rl0laqgTbIMFyvqAg50l~--LS9kdle6EjiU0d6kky58AM~wns4WBUhNgAujrGWX2jwV4sVjLKm4alSpZZU6J1btlriw__",
            "Tony Stark created the Iron Man suit which is equipped with various weapons.Iron Man is a businessman and entrepreneur who seeks to innovate and improve his technology, both for society's benefit and his own.",
            Color(0xFFA0060D))
        "Deadpool" -> Hero(
            "Deadpool",
            "https://images.hdqwalls.com/download/deadpool-katana-chaos-et-1080x2400.jpg",
            "Deadpool is a highly trained assassin and mercenary. He is adept in multiple forms of martial arts, including Savate. Deadpool is an extraordinary athlete, and an expert swordsman and marksman. He is skilled in the use of multiple weapons, including katanas, knives, grenades, and guns. ",
            Color(0xFF630206))
        "Thor" -> Hero(
            "Thor",
            "https://images.wallpapersden.com/image/download/hd-thor-love-and-thunder-movie_bWlobmyUmZqaraWkpJRmZW1lrWdpZWU.jpg",
            "God of thunder from Valhalla",
            Color(0xFF0C4B7C))
        "Hulk" -> Hero(
            "Hulk",
            "https://images.hdqwalls.com/download/hulk-the-beast-4k-j0-1080x2400.jpg",
            "Bruce Banner is hit with the blast, absorbing massive amounts of gamma radiation. He awakens later seemingly unscathed, but he begins transforming into a powerful and destructive creature upon nightfall, which a pursuing soldier describes as a hulk.",
            Color(0xFF1C741F))
        "Black Panther" -> Hero(
            "Black Panther",
            "https://images.wallpapersden.com/image/download/marvel-black-panther-artwork_a2ZqbmmUmZqaraWkpJRmZW1lrWdpZWU.jpg",
            "T'Challa, also known as Black Panther. He is the king of t Wakanda, a technologically advanced country with a rich supply of vibranium, a powerful and versatile metal. T'Challa is known for his intelligence, combat skills, and enhanced abilities granted by the heart-shaped herb.",
            Color(0xFF0A134E))
        "Vision" -> Hero(
            "Vision",
            "https://images.wallpapersden.com/image/download/wandavision-4k-vision-art_bWdtaGqUmZqaraWkpJRmZW1lrWdpZWU.jpg",
            "Vision is a synthetic humanoid. He is known for his unique origin, impressive abilities, and significant role in various storylines involving the Avengers.",
            Color(0xFFE91E63))
        "Thanos" -> Hero(
            "Thanos",
            "https://images.hdqwalls.com/download/thanos-new-4k-fp-1080x2400.jpg",
            "Thanos is one of the most powerful and iconic supervillains. Known as the Mad Titan. hHe collects the six Infinity Gems and mounts them on his gauntlet to become omnipotent. He uses this power to erase half of all life in the universe to impress Death",
            Color(0xFF550CA7))
        else -> Hero("Unknown", "", "No description available.", Color.Gray)
    }
}
