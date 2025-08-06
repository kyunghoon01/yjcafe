package yjcafe.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import yjcafe.Page;
import yjcafe.YJCafe;
import yjcafe.model.MenuImageVO;
import yjcafe.model.MenuSubVO;

import yjcafe.model.CoffeeInfoDAO;


// FrameFormat 클래스 이름은 개인적으로 변경하여 사용하시면 됩니다.
public class CoffeeInfo extends Page {
	static class Param {
		public int h_number;
	}

	private static final long serialVersionUID = 5800190544032814016L;
	private static final int FRAME_WIDTH = 550;
	// 전체 프로그램의 너비는 600이지만 내부에서 사용하게 될 너비는
	// 이보다 더 작아야 하기 때문에 600에서 50을 낮춘 550을 사용하였습니다.
	CoffeeInfoDAO CoffeeInfoDao = new CoffeeInfoDAO();
	private int h_number;
	private CenterPanel center = new CenterPanel();
	private BottomPanel bottom = new BottomPanel();

	public CoffeeInfo() {
		super("카페 메뉴정보", "영진카페 예약 프로그램");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(center);
		add(Box.createVerticalStrut(10));
		add(bottom);

	}

	@Override
	public void onMount(Object param) {
		Param params = castParam(param);
		h_number = params.h_number;
		System.out.println("The current h_number is: " + h_number);
		try {
			center.load(h_number);
			bottom.load(h_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 아래 CenterPanel과 BottomPanel 클래스들은 샘플로 만들어둔 클래스입니다. 불필요하면 삭제하시면 됩니다.
	// 다만, 개인적으로 클래스를 만드는 경우 아래 코드처럼 setPreferredSize(new Dimension(FRAME_WIDTH,
	// 높이));
	// 함수를 통해 너비를 FRAME_WIDTH로 맞춰줘야 합니다.
	private class CenterPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		Thread th;
		Font font = new Font("Monospaced", Font.BOLD, 15);

		public CenterPanel() {

			setPreferredSize(new Dimension(FRAME_WIDTH, 320));
			setBorder(BorderFactory.createLineBorder(Color.BLACK));

			setLayout(new FlowLayout(FlowLayout.CENTER));
			requestFocus();
			setFocusable(true);
		}

		public void load(int cafenum) throws IOException, InterruptedException {
			removeAll();

			int x = Integer.parseInt(CoffeeInfoDao.readm_count(cafenum));
			// centerpane1는 슬라이더 패널
			JPanel centerpanel1 = new JPanel(new BorderLayout(0, 0));
			centerpanel1.setPreferredSize(new Dimension(530, 160));

			// 중앙 그림 페이지
			ArrayList<MenuImageVO> art = new ArrayList<MenuImageVO>();
			art = CoffeeInfoDao.readm_imagelist(cafenum);

			JPanel ImgPanel = new JPanel();
			centerpanel1.add(ImgPanel, BorderLayout.CENTER);

			ImageIcon[] img_slider1 = new ImageIcon[x];
			ImageIcon[] img_slider2 = new ImageIcon[x];
			ImageIcon[] img_slider3 = new ImageIcon[x];

			for (int a = 0; a < x; a++) {
				URL url = new URL(art.get(a).getM_image());
				BufferedImage image = ImageIO.read(url);

				ImageIcon imageIcon = new ImageIcon(image);
				Image img = imageIcon.getImage();
				Image updateImg = img.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
				img_slider1[a] = new ImageIcon(updateImg);

			}

			for (int a = 0; a < x - 1; a++) {
				URL url = new URL(art.get(a + 1).getM_image());
				BufferedImage image = ImageIO.read(url);

				ImageIcon imageIcon = new ImageIcon(image);
				Image img = imageIcon.getImage();
				Image updateImg = img.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
				img_slider2[a] = new ImageIcon(updateImg);

			}

			for (int a = 0; a < x - 2; a++) {
				URL url = new URL(art.get(a + 2).getM_image());
				BufferedImage image = ImageIO.read(url);

				ImageIcon imageIcon = new ImageIcon(image);
				Image img = imageIcon.getImage();
				Image updateImg = img.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
				img_slider3[a] = new ImageIcon(updateImg);

			}
			URL url1 = new URL(art.get(0).getM_image());
			BufferedImage image1 = ImageIO.read(url1);

			ImageIcon imageIcon1 = new ImageIcon(image1);
			Image img1 = imageIcon1.getImage();
			Image updateImg1 = img1.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
			img_slider2[8] = new ImageIcon(updateImg1);

			URL url2 = new URL(art.get(0).getM_image());
			BufferedImage image2 = ImageIO.read(url2);

			ImageIcon imageIcon2 = new ImageIcon(image2);
			Image img2 = imageIcon2.getImage();
			Image updateImg2 = img2.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
			img_slider3[8] = new ImageIcon(updateImg2);

			URL url3 = new URL(art.get(1).getM_image());
			BufferedImage image3 = ImageIO.read(url3);

			ImageIcon imageIcon3 = new ImageIcon(image3);
			Image img3 = imageIcon3.getImage();
			Image updateImg3 = img3.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
			img_slider3[8] = new ImageIcon(updateImg3);

			JButton button_slider1 = new JButton();
			button_slider1.setPreferredSize(new Dimension(150, 130));
			button_slider1.setBorderPainted(false);
			button_slider1.setContentAreaFilled(false);
			button_slider1.setFocusPainted(false);
			button_slider1.setIcon(img_slider1[0]);

			ImgPanel.add(button_slider1);

			JButton button_slider2 = new JButton();
			button_slider2.setPreferredSize(new Dimension(150, 130));
			button_slider2.setBorderPainted(false);
			button_slider2.setContentAreaFilled(false);
			button_slider2.setFocusPainted(false);
			button_slider2.setIcon(img_slider2[0]);

			ImgPanel.add(button_slider2);

			JButton button_slider3 = new JButton();
			button_slider3.setPreferredSize(new Dimension(150, 130));
			button_slider3.setBorderPainted(false);
			button_slider3.setContentAreaFilled(false);
			button_slider3.setFocusPainted(false);
			button_slider3.setIcon(img_slider3[0]);

			ImgPanel.add(button_slider3);

			JRadioButton[] img_JRadio = new JRadioButton[x];
			for (int t = 0; t < x; t++) {
				img_JRadio[t] = new JRadioButton();
				img_JRadio[t].setBorderPainted(false);
				img_JRadio[t].setContentAreaFilled(false);
				img_JRadio[t].setFocusPainted(false);
				img_JRadio[t].setEnabled(false);
				ImgPanel.add(img_JRadio[t]);
			}
			img_JRadio[0].setSelected(true);

			// 왼쪽 버튼
			JPanel LeftButPanel = new JPanel();
			centerpanel1.add(LeftButPanel, BorderLayout.WEST);
			LeftButPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 60));
			JButton leftbut = new JButton("<");
			leftbut.setPreferredSize(new Dimension(20, 40));
			leftbut.setFont(font);
			leftbut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (img_JRadio[0].isSelected() == true) {
						img_JRadio[0].setSelected(false);
						img_JRadio[8].setSelected(true);
						button_slider1.setIcon(img_slider1[8]);
						button_slider2.setIcon(img_slider2[8]);
						button_slider3.setIcon(img_slider3[8]);
					} else if (img_JRadio[1].isSelected() == true) {
						img_JRadio[1].setSelected(false);
						img_JRadio[0].setSelected(true);
						button_slider1.setIcon(img_slider1[0]);
						button_slider2.setIcon(img_slider2[0]);
						button_slider3.setIcon(img_slider3[0]);
					} else if (img_JRadio[2].isSelected() == true) {
						img_JRadio[2].setSelected(false);
						img_JRadio[1].setSelected(true);
						button_slider1.setIcon(img_slider1[1]);
						button_slider2.setIcon(img_slider2[1]);
						button_slider3.setIcon(img_slider3[1]);
					} else if (img_JRadio[3].isSelected() == true) {
						img_JRadio[3].setSelected(false);
						img_JRadio[2].setSelected(true);
						button_slider1.setIcon(img_slider1[2]);
						button_slider2.setIcon(img_slider2[2]);
						button_slider3.setIcon(img_slider3[2]);
					} else if (img_JRadio[4].isSelected() == true) {
						img_JRadio[4].setSelected(false);
						img_JRadio[3].setSelected(true);
						button_slider1.setIcon(img_slider1[3]);
						button_slider2.setIcon(img_slider2[3]);
						button_slider3.setIcon(img_slider3[3]);
					} else if (img_JRadio[5].isSelected() == true) {
						img_JRadio[5].setSelected(false);
						img_JRadio[4].setSelected(true);
						button_slider1.setIcon(img_slider1[4]);
						button_slider2.setIcon(img_slider2[4]);
						button_slider3.setIcon(img_slider3[4]);
					} else if (img_JRadio[6].isSelected() == true) {
						img_JRadio[6].setSelected(false);
						img_JRadio[5].setSelected(true);
						button_slider1.setIcon(img_slider1[5]);
						button_slider2.setIcon(img_slider2[5]);
						button_slider3.setIcon(img_slider3[5]);
					} else if (img_JRadio[7].isSelected() == true) {
						img_JRadio[7].setSelected(false);
						img_JRadio[6].setSelected(true);
						button_slider1.setIcon(img_slider1[6]);
						button_slider2.setIcon(img_slider2[6]);
						button_slider3.setIcon(img_slider3[6]);
					} else if (img_JRadio[8].isSelected() == true) {
						img_JRadio[8].setSelected(false);
						img_JRadio[7].setSelected(true);
						button_slider1.setIcon(img_slider1[7]);
						button_slider2.setIcon(img_slider2[7]);
						button_slider3.setIcon(img_slider3[7]);
					}

				}
			});
			LeftButPanel.add(leftbut);

			// 오른쪽 버튼
			JPanel RightButPanel = new JPanel();
			RightButPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 60));

			centerpanel1.add(RightButPanel, BorderLayout.EAST);
			JButton rightbut = new JButton(">");
			rightbut.setFont(font);
			rightbut.setPreferredSize(new Dimension(20, 40));
			RightButPanel.add(rightbut);
			rightbut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (img_JRadio[8].isSelected() == true) {
						img_JRadio[8].setSelected(false);
						img_JRadio[0].setSelected(true);
						button_slider1.setIcon(img_slider1[0]);
						button_slider2.setIcon(img_slider2[0]);
						button_slider3.setIcon(img_slider3[0]);
					} else if (img_JRadio[0].isSelected() == true) {
						img_JRadio[0].setSelected(false);
						img_JRadio[1].setSelected(true);
						button_slider1.setIcon(img_slider1[1]);
						button_slider2.setIcon(img_slider2[1]);
						button_slider3.setIcon(img_slider3[1]);
					} else if (img_JRadio[1].isSelected() == true) {
						img_JRadio[1].setSelected(false);
						img_JRadio[2].setSelected(true);
						button_slider1.setIcon(img_slider1[2]);
						button_slider2.setIcon(img_slider2[2]);
						button_slider3.setIcon(img_slider3[2]);
					} else if (img_JRadio[2].isSelected() == true) {
						img_JRadio[2].setSelected(false);
						img_JRadio[3].setSelected(true);
						button_slider1.setIcon(img_slider1[3]);
						button_slider2.setIcon(img_slider2[3]);
						button_slider3.setIcon(img_slider3[3]);
					} else if (img_JRadio[3].isSelected() == true) {
						img_JRadio[3].setSelected(false);
						img_JRadio[4].setSelected(true);
						button_slider1.setIcon(img_slider1[4]);
						button_slider2.setIcon(img_slider2[4]);
						button_slider3.setIcon(img_slider3[4]);
					} else if (img_JRadio[4].isSelected() == true) {
						img_JRadio[4].setSelected(false);
						img_JRadio[5].setSelected(true);
						button_slider1.setIcon(img_slider1[5]);
						button_slider2.setIcon(img_slider2[5]);
						button_slider3.setIcon(img_slider3[5]);
					} else if (img_JRadio[5].isSelected() == true) {
						img_JRadio[5].setSelected(false);
						img_JRadio[6].setSelected(true);
						button_slider1.setIcon(img_slider1[6]);
						button_slider2.setIcon(img_slider2[6]);
						button_slider3.setIcon(img_slider3[6]);
					} else if (img_JRadio[6].isSelected() == true) {
						img_JRadio[6].setSelected(false);
						img_JRadio[7].setSelected(true);
						button_slider1.setIcon(img_slider1[7]);
						button_slider2.setIcon(img_slider2[7]);
						button_slider3.setIcon(img_slider3[7]);
					} else if (img_JRadio[7].isSelected() == true) {
						img_JRadio[7].setSelected(false);
						img_JRadio[8].setSelected(true);
						button_slider1.setIcon(img_slider1[8]);
						button_slider2.setIcon(img_slider2[8]);
						button_slider3.setIcon(img_slider3[8]);
					}

				}
			});

			add(centerpanel1);

			Runnable rr = () -> {
				try {
					while (true) {
						Thread.sleep(4000);
						if (img_JRadio[0].isSelected() == true) {
							img_JRadio[0].setSelected(false);
							img_JRadio[1].setSelected(true);
							img_JRadio[2].setSelected(false);
							img_JRadio[3].setSelected(false);
							img_JRadio[4].setSelected(false);
							img_JRadio[5].setSelected(false);
							img_JRadio[6].setSelected(false);
							img_JRadio[7].setSelected(false);
							img_JRadio[8].setSelected(false);
							button_slider1.setIcon(img_slider1[1]);
							button_slider2.setIcon(img_slider2[1]);
							button_slider3.setIcon(img_slider3[1]);
						} else if (img_JRadio[1].isSelected() == true) {
							img_JRadio[0].setSelected(false);
							img_JRadio[1].setSelected(false);
							img_JRadio[2].setSelected(true);
							img_JRadio[3].setSelected(false);
							img_JRadio[4].setSelected(false);
							img_JRadio[5].setSelected(false);
							img_JRadio[6].setSelected(false);
							img_JRadio[7].setSelected(false);
							img_JRadio[8].setSelected(false);
							button_slider1.setIcon(img_slider1[2]);
							button_slider2.setIcon(img_slider2[2]);
							button_slider3.setIcon(img_slider3[2]);
						} else if (img_JRadio[2].isSelected() == true) {
							img_JRadio[0].setSelected(false);
							img_JRadio[1].setSelected(false);
							img_JRadio[2].setSelected(false);
							img_JRadio[3].setSelected(true);
							img_JRadio[4].setSelected(false);
							img_JRadio[5].setSelected(false);
							img_JRadio[6].setSelected(false);
							img_JRadio[7].setSelected(false);
							img_JRadio[8].setSelected(false);
							button_slider1.setIcon(img_slider1[3]);
							button_slider2.setIcon(img_slider2[3]);
							button_slider3.setIcon(img_slider3[3]);
						} else if (img_JRadio[3].isSelected() == true) {
							img_JRadio[0].setSelected(false);
							img_JRadio[1].setSelected(false);
							img_JRadio[2].setSelected(false);
							img_JRadio[3].setSelected(false);
							img_JRadio[4].setSelected(true);
							img_JRadio[5].setSelected(false);
							img_JRadio[6].setSelected(false);
							img_JRadio[7].setSelected(false);
							img_JRadio[8].setSelected(false);
							button_slider1.setIcon(img_slider1[4]);
							button_slider2.setIcon(img_slider2[4]);
							button_slider3.setIcon(img_slider3[4]);
						} else if (img_JRadio[4].isSelected() == true) {
							img_JRadio[0].setSelected(false);
							img_JRadio[1].setSelected(false);
							img_JRadio[2].setSelected(false);
							img_JRadio[3].setSelected(false);
							img_JRadio[4].setSelected(false);
							img_JRadio[5].setSelected(true);
							img_JRadio[6].setSelected(false);
							img_JRadio[7].setSelected(false);
							img_JRadio[8].setSelected(false);
							button_slider1.setIcon(img_slider1[5]);
							button_slider2.setIcon(img_slider2[5]);
							button_slider3.setIcon(img_slider3[5]);
						} else if (img_JRadio[5].isSelected() == true) {
							img_JRadio[0].setSelected(false);
							img_JRadio[1].setSelected(false);
							img_JRadio[2].setSelected(false);
							img_JRadio[3].setSelected(false);
							img_JRadio[4].setSelected(false);
							img_JRadio[5].setSelected(false);
							img_JRadio[6].setSelected(true);
							img_JRadio[7].setSelected(false);
							img_JRadio[8].setSelected(false);
							button_slider1.setIcon(img_slider1[6]);
							button_slider2.setIcon(img_slider2[6]);
							button_slider3.setIcon(img_slider3[6]);
						} else if (img_JRadio[6].isSelected() == true) {
							img_JRadio[0].setSelected(false);
							img_JRadio[1].setSelected(false);
							img_JRadio[2].setSelected(false);
							img_JRadio[3].setSelected(false);
							img_JRadio[4].setSelected(false);
							img_JRadio[5].setSelected(false);
							img_JRadio[6].setSelected(false);
							img_JRadio[7].setSelected(true);
							img_JRadio[8].setSelected(false);
							button_slider1.setIcon(img_slider1[7]);
							button_slider2.setIcon(img_slider2[7]);
							button_slider3.setIcon(img_slider3[7]);
						} else if (img_JRadio[7].isSelected() == true) {
							img_JRadio[0].setSelected(false);
							img_JRadio[1].setSelected(false);
							img_JRadio[2].setSelected(false);
							img_JRadio[3].setSelected(false);
							img_JRadio[4].setSelected(false);
							img_JRadio[5].setSelected(false);
							img_JRadio[6].setSelected(false);
							img_JRadio[7].setSelected(false);
							img_JRadio[8].setSelected(true);
							button_slider1.setIcon(img_slider1[8]);
							button_slider2.setIcon(img_slider2[8]);
							button_slider3.setIcon(img_slider3[8]);
						} else if (img_JRadio[8].isSelected() == true) {
							img_JRadio[0].setSelected(true);
							img_JRadio[1].setSelected(false);
							img_JRadio[2].setSelected(false);
							img_JRadio[3].setSelected(false);
							img_JRadio[4].setSelected(false);
							img_JRadio[5].setSelected(false);
							img_JRadio[6].setSelected(false);
							img_JRadio[7].setSelected(false);
							img_JRadio[8].setSelected(false);
							button_slider1.setIcon(img_slider1[0]);
							button_slider2.setIcon(img_slider2[0]);
							button_slider3.setIcon(img_slider3[0]);
						}

					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			};

			th = new Thread(rr);
			th.start();

			// centerpane2는 정보 패널 (슬라이더 아래패널)
			JPanel centerpanel2 = new JPanel(new GridLayout(2, 1, 0, 0));
			centerpanel2.setPreferredSize(new Dimension(530, 140));

			add(centerpanel2);
			// 호텔정보
			JPanel centersub1 = new JPanel(new GridLayout(2, 1, 0, 15));
			centerpanel2.add(centersub1);

			JLabel centerlabel1 = new JLabel(CoffeeInfoDao.readk_name(cafenum));
			centerlabel1.setFont(new Font("굴림", Font.BOLD, 18));
			centersub1.add(centerlabel1);

			JLabel centerlabel2 = new JLabel("주소 : " + CoffeeInfoDao.readk_location(cafenum));
			centerlabel2.setFont(new Font("굴림", Font.BOLD, 15));
			centersub1.add(centerlabel2);
			
			

			// Create a sub-panel to hold the address label and the Order button
			JPanel addressPanel = new JPanel(new BorderLayout());

			// Add the address label to the sub-panel
			addressPanel.add(centerlabel2, BorderLayout.CENTER);

			// Add the Order button to the sub-panel
			//addressPanel.add(orderButton, BorderLayout.EAST);

			// Add the sub-panel to centersub1 instead of directly adding centerlabel2
			centersub1.add(addressPanel);


			// 체크인아웃 정보
			JPanel centersub2 = new JPanel(new FlowLayout());
			centersub2.setPreferredSize(new Dimension(FRAME_WIDTH, 80));
			centerpanel2.add(centersub2);

			JPanel checkinoutPanel = new JPanel(new GridLayout(2, 2));
			checkinoutPanel.setPreferredSize(new Dimension(530, 60));
			centersub2.add(checkinoutPanel);

			JLabel c1 = new JLabel(" OPEN TIME ");
			JLabel c2 = new JLabel(" CLOSE TIME ");
			JLabel c3 = new JLabel(CoffeeInfoDao.readk_open_time(cafenum));
			JLabel c4 = new JLabel(CoffeeInfoDao.readk_close_time(cafenum));

			c1.setHorizontalAlignment(JLabel.CENTER);
			c2.setHorizontalAlignment(JLabel.CENTER);
			c3.setHorizontalAlignment(JLabel.CENTER);
			c4.setHorizontalAlignment(JLabel.CENTER);

			checkinoutPanel.add(c1);
			checkinoutPanel.add(c2);
			checkinoutPanel.add(c3);
			checkinoutPanel.add(c4);
			checkinoutPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		

	}
	

	private class BottomPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public BottomPanel() {
			setPreferredSize(new Dimension(FRAME_WIDTH, 320));

		}

		public void load(int cafenum) throws IOException {
			removeAll();
			
			int x = Integer.parseInt(CoffeeInfoDao.readm_count(cafenum));
			JPanel botpanel1 = new JPanel(new GridLayout(x, 1, 0, 10));

			JScrollPane scrollPane = new JScrollPane(botpanel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setPreferredSize(new Dimension(550, 320));

			JPanel CoffeePanel[] = new JPanel[x];
			JPanel CoffeeLeftPanel[] = new JPanel[x];
			ImageIcon Ri[] = new ImageIcon[x];
			JLabel Coffeeimg[] = new JLabel[x];

			JPanel CoffeeMidPanel[] = new JPanel[x];
			JLabel Coffeename[] = new JLabel[x];
			JLabel Coffeearea[] = new JLabel[x];
			JLabel Coffeecost[] = new JLabel[x];
			JLabel Coffeemaxpeo[] = new JLabel[x];

			JPanel CoffeeRightPanel[] = new JPanel[x];
			JButton Coffeebut[] = new JButton[x];

			ArrayList<MenuSubVO> arr = new ArrayList<MenuSubVO>();
			arr = CoffeeInfoDao.readM_Data(cafenum);
			ArrayList<MenuImageVO> art = new ArrayList<MenuImageVO>();
			art = CoffeeInfoDao.readm_imagelist(cafenum);

			for (int a = 0; a < x; a++) {
				CoffeePanel[a] = new JPanel(new BorderLayout(10, 0));
				CoffeePanel[a].setPreferredSize(new Dimension(550, 120));
				botpanel1.add(CoffeePanel[a]);

				// 패널 왼쪽
				CoffeeLeftPanel[a] = new JPanel();
				CoffeePanel[a].add(CoffeeLeftPanel[a], BorderLayout.WEST);

				URL url = new URL(art.get(a).getM_image());
				BufferedImage image = ImageIO.read(url);

				ImageIcon imageIcon = new ImageIcon(image);
				Image img = imageIcon.getImage();
				Image updateImg = img.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
				Ri[a] = new ImageIcon(updateImg);

				Coffeeimg[a] = new JLabel(Ri[a]);
				Coffeeimg[a].setPreferredSize(new Dimension(120, 120));
				CoffeeLeftPanel[a].add(Coffeeimg[a]);

				// 패널 중앙
				CoffeeMidPanel[a] = new JPanel(new GridLayout(4, 1));
				CoffeePanel[a].add(CoffeeMidPanel[a], BorderLayout.CENTER);

				Coffeename[a] = new JLabel("" + arr.get(a).getM_name());
				CoffeeMidPanel[a].add(Coffeename[a]);

				Coffeearea[a] = new JLabel("설명 : " + arr.get(a).getM_description());
				CoffeeMidPanel[a].add(Coffeearea[a]);

		

				Coffeecost[a] = new JLabel("가격 : " + arr.get(a).getM_price());
				CoffeeMidPanel[a].add(Coffeecost[a]);

				CoffeeRightPanel[a] = new JPanel(new BorderLayout());
				CoffeePanel[a].add(CoffeeRightPanel[a], BorderLayout.EAST);
				Coffeebut[a] = new JButton("예약하기");
				final int buttonIndex = a;
				Coffeebut[a].setName(arr.get(a).getM_number() + "");
				Coffeebut[a].addActionListener(e -> {
					int r_number = Integer.parseInt(Coffeebut[buttonIndex].getName());

					DateReservation.Param param = new DateReservation.Param();
					param.r_number = r_number;
					param.h_number = h_number;

					YJCafe.route("reservation", param);
				});
				Coffeebut[a].setPreferredSize(new Dimension(100, 40));
				CoffeeRightPanel[a].add(Coffeebut[a], BorderLayout.SOUTH);
			

			}

			add(scrollPane);
		}
	}

}