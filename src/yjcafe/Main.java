package yjcafe;

import yjcafe.model.UserVO;
import yjcafe.page.CouponPage;
import yjcafe.page.DateReservation;
import yjcafe.page.MainPage;
import yjcafe.page.MyPage;
import yjcafe.page.Notice;
import yjcafe.page.PayInfo;
import yjcafe.page.Rank;
import yjcafe.page.CoffeeInfo;
import yjcafe.page.Search;


//import yjcafe.page.basket;

public class Main {
	// 디버깅 과정에서 로그인을 스킵할지 여부
	// jar로 내보낼 땐 false 필수
	private static final boolean DEBUG_PASS_LOGIN = false;

	public static void main(String[] args) {
		// DB connection 이 오래걸리는 PC를 위해 쓰레드로 미리 실행
		yjcafe.YJCafe.connectAsync();
		try {
			// 사용할 페이지 인스턴스 생성
			MainPage mainpage = new MainPage();
			DateReservation reservation = new DateReservation();
			MyPage mypage = new MyPage();
			CouponPage couponPage = new CouponPage();
			Search search = new Search();
			Rank rank = new Rank();
			Notice notice = new Notice();
			CoffeeInfo coffeeInfo = new CoffeeInfo();
			PayInfo payInfo = new PayInfo();
			
			//basket basketInfo = new basket();

			/*
			 * YJCafe.addRoute("home", home); 으로 등록 시 YJCafe.route("home"); 으로 이동 가능.
			 * 
			 * 이동에 파라미터가 필요한 경우 YJCafe.route("home", 파라미터) 형태로 사용가능.
			 */
			yjcafe.YJCafe.addRoute("mainpage", mainpage);
			yjcafe.YJCafe.addRoute("reservation", reservation);
			yjcafe.YJCafe.addRoute("mypage", mypage);
			yjcafe.YJCafe.addRoute("coupon", couponPage);
			yjcafe.YJCafe.addRoute("search", search);
			yjcafe.YJCafe.addRoute("rank", rank);
			yjcafe.YJCafe.addRoute("notice", notice);
			yjcafe.YJCafe.addRoute("coffeeinfo", coffeeInfo);
			yjcafe.YJCafe.addRoute("payinfo", payInfo);
			
			//YJCafe.addRoute("basketinfo", basketInfo);

			// 기본 화면 home으로 라우팅
			yjcafe.YJCafe.setDefaultPath("mainpage");

			if (DEBUG_PASS_LOGIN) {
				UserVO user = new UserVO();
				//user.id = "gildong";
				//user.number = 1;
				yjcafe.YJCafe.setUser(user);
				// 실제 프레임 실행 부, 싱글턴 패턴이 적용되어 있음.
				yjcafe.YJCafe.launch();
			} else {
				// 로그인 창 실행
				yjcafe.YJCafe.launchLogin();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}