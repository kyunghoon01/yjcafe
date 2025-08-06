package yjcafe.page;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import yjcafe.Page;
import yjcafe.YJCafe;
import yjcafe.model.SearchDAO;
import yjcafe.model.SearchVO;

// FrameFormat 클래스 이름은 개인적으로 변경하여 사용하시면 됩니다.
public class Search extends Page {
	private static final long serialVersionUID = -3944285343434235020L;
	private static final int FRAME_WIDTH = 550;
	// 전체 프로그램의 너비는 600이지만 내부에서 사용하게 될 너비는
	// 이보다 더 작아야 하기 때문에 600에서 50을 낮춘 550을 사용하였습니다.
	private Display display = new Display();

	public Search() {
		super("카페 리스트", "영진카페 예약 프로그램");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(display);
	}

	@Override
	public void onMount(Object param) {
		try {
			display.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class Display extends JPanel {
		private static final long serialVersionUID = 1L;

		BufferedImage image;
		URL url;

		int h_num;
		int cnt4 = 0; // 검색 버튼 클릭시 몇 개의 행을 가져오는지 확인하기 위한 변수.
		int x = 2; // 스크롤 패널에 15개의 패널을 넣기 위한 변수.
		int a; // 스크롤 패널에 패널을 넣기 위한 변수.

		public Display() {
			setPreferredSize(new Dimension(FRAME_WIDTH, 660));
			setLayout(null);
		}

		public void load() throws IOException {
			removeAll();
		

			JPanel botpanel1 = new JPanel(new GridLayout(x, 1, 0, 10));

			JScrollPane scrollPane = new JScrollPane(botpanel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(0, 50, 550, 590); 
			scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			scrollPane.getVerticalScrollBar().setUnitIncrement(9);

			JPanel CoffeePanel[] = new JPanel[x];
			JPanel CoffeeLeftPanel[] = new JPanel[x];
			JLabel Coffeeimg[] = new JLabel[x];

			JPanel CoffeeMidPanel[] = new JPanel[x];
			JLabel Coffeename[] = new JLabel[x];
			JLabel Coffeearea[] = new JLabel[x];
			JLabel Coffeelocation[] = new JLabel[x];
			JLabel Coffeefurniture[] = new JLabel[x];

			JPanel CoffeeRightPanel[] = new JPanel[x];
			JButton Coffeebut[] = new JButton[x];

			for (a = 0; a < x; a++) {
				CoffeePanel[a] = new JPanel(new BorderLayout(10, 0));
				CoffeePanel[a].setPreferredSize(new Dimension(550, 120));
				botpanel1.add(CoffeePanel[a]);
				// 패널 왼쪽
				CoffeeLeftPanel[a] = new JPanel();
				CoffeePanel[a].add(CoffeeLeftPanel[a], BorderLayout.WEST);
				Coffeeimg[a] = new JLabel();
				Coffeeimg[a].setPreferredSize(new Dimension(120, 120));
				CoffeeLeftPanel[a].add(Coffeeimg[a]);

				// 패널 중앙
				CoffeeMidPanel[a] = new JPanel(new GridLayout(5, 1));
				CoffeePanel[a].add(CoffeeMidPanel[a], BorderLayout.CENTER);
				Coffeename[a] = new JLabel("카페 이름");
				CoffeeMidPanel[a].add(Coffeename[a]);
				Coffeearea[a] = new JLabel("소개:");
				CoffeeMidPanel[a].add(Coffeearea[a]);
				Coffeelocation[a] = new JLabel("위치:");
				CoffeeMidPanel[a].add(Coffeelocation[a]);
				Coffeefurniture[a] = new JLabel("min ~ max");
				CoffeeMidPanel[a].add(Coffeefurniture[a]);

				// 패널 오른쪽
				CoffeeRightPanel[a] = new JPanel(new BorderLayout());
				CoffeePanel[a].add(CoffeeRightPanel[a], BorderLayout.EAST);
				Coffeebut[a] = new JButton("상세보기");
				Coffeebut[a].setPreferredSize(new Dimension(100, 40));
				CoffeeRightPanel[a].add(Coffeebut[a], BorderLayout.SOUTH);

			}

			SearchDAO dao = new SearchDAO(); // DB 쿼리문을 가져오기 위해 SearchDAO의 클래스를 dao에 담음.
			int cnt = 0; // ArrayList의 하나의 행을 받아올 때마다 1 증가(몇 개의 행을 가져왔는지 확인하기 위함.)
			int cnt2 = 0; // ArrayList의 하나의 행을 받아올 때마다 1 증가(몇 개의 행을 가져왔는지 확인하기 위함.)
			int cnt3 = 0; // ArrayList의 하나의 행을 받아올 때마다 1 증가(몇 개의 행을 가져왔는지 확인하기 위함.)
			int cnt1 = 0;
			int r_people = 0; // 스크롤 패널의 최소 인원을 표기하기 위함.
			int r_people1 = 0; // 스크롤 패널의 최대 인원을 표기하기 위함.
			int r_cost = 0; // 스크롤 패널의 최소 가격을 표기하기 위함.
			int r_cost1 = 0; // 스크롤 패널의 최소 가격을 표기하기 위함.
			
			//int k_number=0;
			String k_description= new String();
			String h_name = new String();
			String k_location = new String();

			ArrayList<SearchVO> getCafeDesc = dao.getCafeDesc(k_description);
			for (SearchVO vo : getCafeDesc) {
				String data = vo.getK_description();
				
				if (data == null) {
					Coffeearea[cnt].setText("카페 정보가 없습니다.");
					
				} else {
					Coffeearea[cnt].setText("소개:"+data );
				}
				cnt++;
			}
			ArrayList<SearchVO> getCafeLocation = dao.getCafeLocation(k_location);
			for (SearchVO vo : getCafeLocation) {
				String data2 = vo.getK_location();
				
				if (data2 == null) {
					Coffeelocation[cnt1].setText("위치 정보가 없습니다.");	
				} else {
					Coffeelocation[cnt1].setText("위치:"+data2 );
				}
				cnt1++;
			}
			ArrayList<SearchVO> cost = dao.cost(r_cost, r_cost1);
			for (SearchVO vo : cost) {
				int max = vo.getR_cost();
				int min = vo.getR_cost1();
				Coffeefurniture[cnt2].setText("가격대: "+ "최소 "+min + "원 ~ " +"최대 "+ max + "원");
				cnt2++;
			}
			ArrayList<SearchVO> name = dao.name(h_name);
			for (SearchVO vo : name) {
				String data = vo.getK_name();
				String img = vo.getK_image();
				url = new URL(img); // 숙소 이미지 DB에서 가져오기
				image = ImageIO.read(url); // 숙소 이미지
				ImageIcon imageIcon = new ImageIcon(image);
				Image i_img = imageIcon.getImage();
				Image updateImg = i_img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
				ImageIcon updateIcon = new ImageIcon(updateImg);

				Coffeeimg[cnt3].setIcon(updateIcon);
				Coffeename[cnt3].setText(data + "");
				Coffeebut[cnt3].setName(vo.getK_number() + "");
				cnt3++;
			}

			add(scrollPane);

	

		
			for (int i = 0; i < 2; i++) { // 상세보기 버튼을 눌렀을때 몇 번 버튼이 입력되었는지 방 상세보기 폼에 값을 보내는 것
				final int buttonIndex = i;
				Coffeebut[i].addActionListener(e -> {
					int h_number = Integer.parseInt(Coffeebut[buttonIndex].getName());
					CoffeeInfo.Param param = new CoffeeInfo.Param();
					param.h_number = h_number;
					YJCafe.route("coffeeinfo", param);
				});
			}

		}

	

	}
}
