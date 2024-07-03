import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.composetutor.R
import kotlin.math.abs

data class Hero(val name: String, val image: String, val description: String, val color: Color)

@Composable
fun HeroItem(hero: Hero, onClick: () -> Unit, isCentered: Boolean) {
    val size by animateDpAsState(targetValue = if (isCentered) 250.dp else 180.dp)
    val height by animateDpAsState(targetValue = if (isCentered) 550.dp else 300.dp)
    val cornerRadius by animateDpAsState(targetValue = if (isCentered) 26.dp else 12.dp)

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .height(height)
            .width(size)
            .clip(RoundedCornerShape(cornerRadius))
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = hero.image),
            contentDescription = hero.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        if (isCentered) {
            Text(
                text = hero.name,
                style = MaterialTheme.typography.h4.copy(
                    color = Color.White,
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroList(
    navController: NavController,
    heroes: List<Hero>,
    lazyListState: LazyListState,
    snapBehavior: FlingBehavior,
) {
    val totalItemCount = Int.MAX_VALUE
    val startIndex = totalItemCount / 2 - (totalItemCount / 2) % heroes.size

    LaunchedEffect(Unit) {
        if (lazyListState.firstVisibleItemIndex == 0) {
            lazyListState.scrollToItem(startIndex)
        }
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        flingBehavior = snapBehavior
    ) {
        items(totalItemCount) { index ->
            val heroIndex = index % heroes.size
            val isCentered = (index - lazyListState.firstVisibleItemIndex) == (lazyListState.layoutInfo.visibleItemsInfo.size / 2)

            HeroItem(hero = heroes[heroIndex], onClick = {
                navController.navigate("detail/${heroes[heroIndex].name}")
            }, isCentered = isCentered)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController) {
    val heroes = listOf(
        Hero(
            "Spider-Man",
            "https://images.wallpapersden.com/image/download/spider-man-ps4_a2dubmuUmZqaraWkpJRmZW1lrWdpZWU.jpg",
            "Peter Parker became Spider-Man when he was bitten by radioactive spider.Peter struggles to help his widowed Aunt May pay the rent, is taunted by Flash, and continues fighting crime and saving the city as Spider-Man",
            Color(0xFFFF000C)
        ),
        Hero(
            "Iron Man",
            "https://s3-alpha-sig.figma.com/img/d6ff/6e53/06e9a778c50e17ebd04b812b3a8258ef?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=mQfkAV4MhIM-hTmFL5YaDWb5GhcHAZHWJMGtaLjWdtTQBr6IejjDWDkaKuXVKt3e74db0WIfWyqwXLo7flHlQY3i2~xihvnZqxckjdhK54720JuTGroV7TkGFriI0v763~au5xooebGlpEQ~Q17e8ZrrB-cH7hAJHqlQSYGmGMUR7c6tfIpAcf15yUxyGEjcMlr3-KX1G1kXXCmWvkFUCK2L3Oqx6DjyYTNgRn8J~Pe0-2O8tNtg7BKyfC5Rl0laqgTbIMFyvqAg50l~--LS9kdle6EjiU0d6kky58AM~wns4WBUhNgAujrGWX2jwV4sVjLKm4alSpZZU6J1btlriw__",
            "Tony Stark created the Iron Man suit which is equipped with various weapons.Iron Man is a businessman and entrepreneur who seeks to innovate and improve his technology, both for society's benefit and his own.",
            Color(0xFFA0060D)
        ),
        Hero(
            "Deadpool",
            "https://images.hdqwalls.com/download/deadpool-katana-chaos-et-1080x2400.jpg",
            "Deadpool is a highly trained assassin and mercenary. He is adept in multiple forms of martial arts, including Savate. Deadpool is an extraordinary athlete, and an expert swordsman and marksman. He is skilled in the use of multiple weapons, including katanas, knives, grenades, and guns. ",
            Color(0xFF630206)
        ),
        Hero(
            "Thor",
            "https://images.wallpapersden.com/image/download/hd-thor-love-and-thunder-movie_bWlobmyUmZqaraWkpJRmZW1lrWdpZWU.jpg",
            "God of thunder from Valhalla",
            Color(0xFF0C4B7C)
        ),
        Hero(
            "Hulk",
            "https://images.hdqwalls.com/download/hulk-the-beast-4k-j0-1080x2400.jpg",
            "Bruce Banner is hit with the blast, absorbing massive amounts of gamma radiation. He awakens later seemingly unscathed, but he begins transforming into a powerful and destructive creature upon nightfall, which a pursuing soldier describes as a hulk.",
            Color(0xFF1C741F)
        ),
        Hero(
            "Black Panther",
            "https://images.wallpapersden.com/image/download/marvel-black-panther-artwork_a2ZqbmmUmZqaraWkpJRmZW1lrWdpZWU.jpg",
            "T'Challa, also known as Black Panther. He is the king of t Wakanda, a technologically advanced country with a rich supply of vibranium, a powerful and versatile metal. T'Challa is known for his intelligence, combat skills, and enhanced abilities granted by the heart-shaped herb.",
            Color(0xFF0A134E)
        ),
        Hero(
            "Vision",
            "https://images.wallpapersden.com/image/download/wandavision-4k-vision-art_bWdtaGqUmZqaraWkpJRmZW1lrWdpZWU.jpg",
            "Vision is a synthetic humanoid. He is known for his unique origin, impressive abilities, and significant role in various storylines involving the Avengers.",
            Color(0xFFE91E63)
        ),
        Hero(
            "Thanos",
            "https://images.hdqwalls.com/download/thanos-new-4k-fp-1080x2400.jpg",
            "Thanos is one of the most powerful and iconic supervillains. Known as the Mad Titan. hHe collects the six Infinity Gems and mounts them on his gauntlet to become omnipotent. He uses this power to erase half of all life in the universe to impress Death",
            Color(0xFF550CA7)
        ),
    )
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    val startIndex = Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2) % heroes.size
    var initialScrollDone by rememberSaveable { mutableStateOf(false) }
    val centerItemIndex by remember {
        derivedStateOf {
            val visibleItems = lazyListState.layoutInfo.visibleItemsInfo
            val centerPosition = lazyListState.layoutInfo.viewportEndOffset / 2
            visibleItems.minByOrNull {
                abs(it.offset + it.size / 2 - centerPosition)
            }?.index ?: 0
        }
    }
    val backgroundColor by remember { derivedStateOf { heroes[centerItemIndex % heroes.size].color } }

    LaunchedEffect(Unit) {
        if (!initialScrollDone) {
            lazyListState.scrollToItem(startIndex)
            initialScrollDone = true
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Black, Color.DarkGray, backgroundColor)
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.marvel_logo),
                contentDescription = "Marvel Logo",
                modifier = Modifier
                    .height(100.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Choose your hero",
                color = Color.White,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 16.dp),
            )
            HeroList(navController, heroes, lazyListState, snapBehavior)
        }
    }
}
