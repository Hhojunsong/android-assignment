package com.mca.androidseminar2026.ui

import androidx.compose.runtime.Composable
import com.mca.androidseminar2026.model.OrderTab
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mca.androidseminar2026.data.RestaurantData
import androidx.compose.foundation.layout.height
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color


// 상태와 이벤트 연결은 CounterExample.kt의 실행 예제를 참고하세요.
// 각 화면에 필요한 상태·콜백과 사용할 ViewModel은 직접 설계하세요.
// 메뉴·장바구니 항목은 MenuComponents.kt의 MenuItemRow를 재사용하세요.
// 예시 파일 구조입니다. 한 파일에 여러 함수를 모두 저장할 필요는 없습니다.

@Composable
fun RestaurantScreen(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    // TODO: RestaurantData의 이름·대표 이미지·소개와 가게 찜 버튼을 표시하세요.
    // 찜 여부를 구분하고, 탭 전환과 화면 회전 후에도 상태를 유지하세요.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(text = "가게 소개", fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = RestaurantData.imageResId),
            contentDescription = RestaurantData.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = RestaurantData.name, fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        Text(RestaurantData.description)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onFavoriteClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFFB7B7C2)),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF62636D)
            )
        ) {
            Text(
                if (isFavorite) "♥ 가게 찜 해제"
                else "♡ 가게 찜하기"
            )
        }
    }
}

@Composable
fun MenuScreen() {
    // TODO: 음식점 이름, 카테고리 선택과 MenuItemRow 목록을 배치하세요.
    // 목록 맨 위에서 당겨 새로고침하면 0.5초간 진행 표시를 보여 주세요.
    // 실제 데이터 변경 없이 기존 상태를 유지하고 중복 새로고침은 막으세요.
}

@Composable
fun CartScreen() {
    // TODO: 같은 MenuItemRow로 수량이 1 이상인 메뉴를 표시하세요.
    // 수량 변경을 연결하고, 수량이 0이면 목록에서 제거하세요.
    // 전체 비우기와 빈 목록 안내를 구현하세요.
    // 전체 수량·총가격 텍스트 대신 Bottom Bar 위에 CheckoutButton을 고정하세요.
    // 클릭 시 현재 금액의 결제 완료 Toast만 표시하고 장바구니는 유지하세요.
    CheckoutButton(totalPrice = 0, enabled = true, onClick = {})
}

@Composable
fun CheckoutButton(totalPrice: Long, enabled: Boolean, onClick: () -> Unit) {
    // TODO: "총 금액 : nnnn원" 버튼을 구현하세요. 빈 장바구니에서는 비활성화하세요.
    Button(
        onClick = onClick,
        enabled = enabled
    ) {
        Text("총 금액 : ${totalPrice}원")
    }

}

@Composable
fun BottomBar(
    selectedTab: OrderTab,
    totalQuantity: Int,
    onTabSelected: (OrderTab) -> Unit,
) {
    // TODO: 세 탭과 선택 상태, 장바구니 전체 수량을 표시하세요.
    // 탭을 누르면 onTabSelected에 해당 OrderTab을 전달하세요.

    NavigationBar {
        NavigationBarItem(
            selected = selectedTab == OrderTab.RESTAURANT,
            onClick = { onTabSelected(OrderTab.RESTAURANT) },
            icon = { Text("가") },
            label = { Text("가게 소개") },
        )

        NavigationBarItem(
            selected = selectedTab == OrderTab.MENU,
            onClick = { onTabSelected(OrderTab.MENU) },
            icon = { Text("메") },
            label = { Text("메뉴") },
        )

        NavigationBarItem(
            selected = selectedTab == OrderTab.CART,
            onClick = { onTabSelected(OrderTab.CART) },
            icon = {
                Text(totalQuantity.toString())
            },
            label = { Text("장바구니") }
        )
    }
}


