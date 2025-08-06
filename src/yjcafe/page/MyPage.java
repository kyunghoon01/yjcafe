package yjcafe.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import yjcafe.Page;
import yjcafe.YJCafe;
import yjcafe.model.UserVO;

public class MyPage extends Page {
	static class Param {
		protected String u_id = null;
	}

	private static final long serialVersionUID = -4814805383787415047L;
	private static final int PANEL_WIDTH = 550;

	private UserVO user = new UserVO();
	private String u_id = null;

	private CenterPanel center = new CenterPanel();
	private BottomPanel bottom = new BottomPanel();

	public MyPage() {
		super("마이페이지", "My Page");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(center);
		add(Box.createVerticalStrut(10));
		add(bottom);
	}

	@Override
	public void onMount(Object param) {
		Param params = (Param) param;
		this.u_id = params.u_id;

		makeConnection();

		try {
			center.load();
			bottom.load();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void makeConnection() {
		try {
			Connection conn = YJCafe.getConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM 회원 WHERE u_id='" + u_id + "'");
			if (rs.next()) {
				user.setU_number(rs.getInt("u_number"));
			      user.setU_name(rs.getString("u_name"));
			      user.setU_id(rs.getString("u_id"));
			      user.setU_password(rs.getString("u_password"));
			      user.setU_phone_number(rs.getString("u_phone_number"));
			      user.setU_birthday(rs.getDate("u_birthday"));
			      user.setU_email(rs.getString("u_email"));
			      user.setU_cash(rs.getInt("u_cash"));
			      user.setU_enroll_date(rs.getDate("u_enroll_date"));
			      user.setU_profile_image(rs.getString("u_profile_image"));
			      user.setU_department(rs.getString("u_department"));

			} else {
				YJCafe.showAlert("회원이 존재하지 않습니다.");
			}
		} catch (SQLException err) {
			YJCafe.showAlert("데이터베이스 처리에 실패했습니다.");
		}
	}

	private class CenterPanel extends JPanel {
		private static final long serialVersionUID = 8169886658779607735L;

		public CenterPanel() {
			setLayout(new FlowLayout(FlowLayout.LEFT));
			setPreferredSize(new Dimension(PANEL_WIDTH, 234));
		}

		public void load() throws Exception {
			removeAll();
			JPanel centerFrame = new JPanel(new BorderLayout(0, 10));
			final ImagePanel imagePanel = new ImagePanel();
			{
				JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				{
					JLabel label = new JLabel(user.getU_name());
					label.setFont(new Font("굴림", Font.BOLD, 20));
					panel.add(label);
				}
				{
					JLabel label = new JLabel("님 반갑습니다!");
					label.setFont(new Font("굴림", Font.PLAIN, 20));
					panel.add(label);
				}
				centerFrame.add(panel, BorderLayout.NORTH);
			}
			{
				JPanel panels[] = { new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)),
						new JPanel(new BorderLayout(20, 0)) };
				{
					imagePanel.setPreferredSize(new Dimension(150, 145));
					Connection conn = YJCafe.getConnection();
					Statement stmt = conn.createStatement();
					String sql = null;
					sql  = "SELECT u_profile_image FROM 회원 WHERE u_id='" + u_id + "'";
					ResultSet rs = stmt.executeQuery(sql);
					if (rs.next())
						imagePanel.image = ImageIO.read(new URL(user.getU_profile_image())).getScaledInstance(150, 145, BufferedImage.SCALE_DEFAULT);
					else
						imagePanel.image = ImageIO.read(new URL("https://cdn.pixabay.com/photo/2015/04/08/07/25/fat-712246_1280.png")).getScaledInstance(150, 145, BufferedImage.SCALE_DEFAULT);
					imagePanel.repaint();
					
					if (stmt != null)
						stmt.close();
					panels[1].add(imagePanel, BorderLayout.WEST);
				}
				{
					JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
					{
						JPanel panel = new JPanel(new GridLayout(5, 1, 0, 10));
						panel.add(new TopicLabel("이메일 주소"));
						panel.add(new TopicLabel("휴대폰 번호"));
						panel.add(new TopicLabel("학번"));
						panel.add(new TopicLabel("학과"));
						panel.add(new TopicLabel("보유금액"));
						contentPanel.add(panel, BorderLayout.WEST);
					}
					{
						JPanel panel = new JPanel(new GridLayout(5, 1, 0, 10));
						panel.add(new ContentLabel(user.getU_email()));
						panel.add(new ContentLabel(user.getU_phone_number()));
						panel.add(new ContentLabel(user.getU_id()));
						
						JPanel cost_panel = new JPanel(new BorderLayout(20, 0));
						cost_panel.add(new ContentLabel(String.valueOf(user.getU_cash())), BorderLayout.CENTER);
						JButton chargeCost = new JButton("충전하기");
						chargeCost.addActionListener(e -> {
							try {
								JDialog dialog = new JDialog();
								dialog.setLayout(new BorderLayout());
								
								JPanel outPanel = new JPanel(new BorderLayout(0, 30));
								outPanel.setPreferredSize(new Dimension(300, 200));
								outPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
								
								JLabel label = new JLabel("얼마를 충전하시겠습니까?");
								label.setFont(new Font("Monospaced", Font.BOLD, 20));
								label.setHorizontalAlignment(JLabel.CENTER);
								outPanel.add(label, BorderLayout.NORTH);
								
								JPanel inPanel = new JPanel();
								JTextField field = new JTextField();
								field.setPreferredSize(new Dimension(140, 50));
								field.setHorizontalAlignment(JTextField.CENTER);
								field.setFont(new Font("Monospaced", Font.BOLD, 20));
								inPanel.add(field);
								outPanel.add(inPanel, BorderLayout.CENTER);
								
								JPanel inPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
								inPanel2.setPreferredSize(new Dimension(300, 50));
								JButton confirmButton = new JButton("충전");
								confirmButton.addActionListener(e2 -> {
									if (!field.getText().equals("")) {
										try {
											Connection conn = YJCafe.getConnection();
											Statement stmt = conn.createStatement();
											String sql = null;
											sql  = "UPDATE 회원 SET u_cash = u_cash + " + Integer.parseInt(field.getText());
											sql += " WHERE u_number = " + user.getU_number();
											stmt.executeUpdate(sql);
											
											dialog.dispose();
											
											if (stmt != null)
												stmt.close();
										} catch (SQLException err) {
											err.printStackTrace();
										}
									}
								});
								JButton cancelButton = new JButton("취소");
								cancelButton.addActionListener(e2 -> {
									dialog.dispose();
								});
								inPanel2.add(confirmButton);
								inPanel2.add(cancelButton);
								outPanel.add(inPanel2, BorderLayout.SOUTH);
								
								dialog.add(outPanel);
								dialog.pack();
								dialog.setLocationRelativeTo(null);
								dialog.setTitle("보유 포인트 충전");
								dialog.setResizable(false);
								dialog.setVisible(true);
								
								Connection conn = YJCafe.getConnection();
								Statement stmt = conn.createStatement();
								
								if (stmt != null)
									stmt.close();
							} catch (SQLException err) {
								err.printStackTrace();
							}
						});
						panel.add(new ContentLabel(user.getU_department()));
						cost_panel.add(chargeCost, BorderLayout.EAST);

						panel.add(cost_panel);
						contentPanel.add(panel, BorderLayout.CENTER);
					}
					panels[1].add(contentPanel, BorderLayout.CENTER);
					panels[0].add(panels[1]);
				}
				centerFrame.add(panels[0], BorderLayout.CENTER);
			}
			{
				JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 28, 0));
				//panel.setBorder(BorderFactory.createEmptyBorder(-2, 0, 0, 0));
				JButton button = new JButton("프로필 이미지 변경");
				button.setFont(new Font("굴림", Font.BOLD, 8));
				button.addActionListener(e -> {
				    JDialog dialog = new JDialog();
				    dialog.setLayout(new BorderLayout());

				    String[] imageUrls = {
				        "https://cdn.pixabay.com/photo/2015/04/08/07/25/fat-712246_1280.png",
				        "https://cdn.pixabay.com/photo/2013/07/13/10/00/face-156456_1280.png",
				        "https://cdn.pixabay.com/photo/2017/01/31/13/05/cameo-2023867_1280.png"
				    };

				    JPanel outsideImagePanel = new JPanel(new GridLayout(1, imageUrls.length, 10, 0));
				    final int SIZE = 120;
				    for (int i = 0; i < imageUrls.length; i++) {
				        ImagePanel insidePanel = new ImagePanel();
				        insidePanel.setPreferredSize(new Dimension(SIZE, SIZE));
				        try {
				            insidePanel.image = ImageIO.read(new URL(imageUrls[i]))
				                    .getScaledInstance(SIZE, SIZE, BufferedImage.SCALE_DEFAULT);
				            insidePanel.repaint();
				        } catch (Exception err) {
				            err.printStackTrace();
				        }
				        outsideImagePanel.add(insidePanel);
				    }

				    JPanel outsideButtonPanel = new JPanel(new GridLayout(1, imageUrls.length, 10, 0));
				    for (int i = 0; i < imageUrls.length; i++) {
				        JButton choiceButton = new JButton("선택하기");
					choiceButton.setName(String.valueOf(i));
					choiceButton.addActionListener(e2 -> {
					    JButton tmpButton = (JButton)e2.getSource();
					    try {
					        imagePanel.image =
					            ImageIO.read(
					                new URL(imageUrls[Integer.parseInt(tmpButton.getName())])
					            ).getScaledInstance(150,
					                                145,
					                                BufferedImage.SCALE_DEFAULT);
					        imagePanel.repaint();

					        Connection conn =
					            YJCafe.getConnection();
					        Statement stmt =
					            conn.createStatement();

					        String sql = "UPDATE 회원 SET u_profile_image ='"
					                     + imageUrls[Integer.parseInt(tmpButton.getName())]
					                     + "' WHERE u_id ='"
					                     + u_id
					                     + "'";
					        stmt.executeUpdate(sql);

					        if(stmt != null)
					            stmt.close();
					    } catch(Exception err) {
					         err.printStackTrace();    
					    }

				       });
				       outsideButtonPanel.add(choiceButton);
				   }
					dialog.add(outsideImagePanel, BorderLayout.CENTER);
					dialog.add(outsideButtonPanel, BorderLayout.SOUTH);
					dialog.pack();
					dialog.setLocationRelativeTo(null);
					dialog.setTitle("프로필 이미지 변경");
					dialog.setResizable(false);
					dialog.setVisible(true);
				});
				panel.add(button);
				centerFrame.add(panel, BorderLayout.SOUTH);
			}
			add(centerFrame);
		}

		private class TopicLabel extends JLabel {
			private static final long serialVersionUID = 1L;

			public TopicLabel(String text) {
				super(text);
				setFont(new Font("굴림", Font.BOLD, 15));
				setHorizontalAlignment(JLabel.CENTER);
			}
		}

		private class ContentLabel extends JLabel {
			private static final long serialVersionUID = 1L;

			public ContentLabel(String text) {
				super(text);
				setFont(new Font("굴림", Font.PLAIN, 15));
			}
		}
	}

	private class BottomPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public BottomPanel() {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(PANEL_WIDTH, 410));
		}

		public void load() throws Exception {
			removeAll();
			JTabbedPane tab = new JTabbedPane();
			tab.setPreferredSize(new Dimension(PANEL_WIDTH, 350));
			Connection conn = YJCafe.getConnection();
			
			{
				JPanel bottomFrame = new JPanel();
				bottomFrame.setLayout(new BoxLayout(bottomFrame, BoxLayout.Y_AXIS));
				JScrollPane scroll2 = new JScrollPane(bottomFrame);
				scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
				tab.addTab("메뉴 예약예정 정보", scroll2);
				
				
				
				Statement stmt = conn.createStatement();
				
				
				ResultSet exist = stmt.executeQuery("SELECT * FROM 예약 WHERE u_number=" + user.getU_number() + " AND b_status='예약중'");
				
			
				 boolean hasReservations = false;
				 
				while (exist.next()) {
					{
						int reservationNumber = exist.getInt("b_number");
						  hasReservations = true;
						JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
						{
							ImagePanel imagePanel = new ImagePanel();
							imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							imagePanel.setPreferredSize(new Dimension(120, 120));
	
							stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery(
								    "SELECT 메뉴_이미지.m_image " +
								    "FROM 메뉴_이미지 " +
								    "INNER JOIN 메뉴 ON 메뉴_이미지.m_number = 메뉴.m_number " +
								    "INNER JOIN 예약 ON 메뉴.m_number = 예약.m_number " +
								    "WHERE 예약.b_number = " + reservationNumber + " AND 예약.b_status = '예약중'"
								);
							if (rs.next()) {
								imagePanel.image = ImageIO.read(new URL(rs.getString("m_image")))
										.getScaledInstance(120, 120, BufferedImage.SCALE_DEFAULT);
								imagePanel.repaint();
							}
							rs.close();
	
							panel.add(imagePanel);
						}
						{
							JPanel content_panel = new JPanel(new GridLayout(3, 1, 0, 20));
	
							stmt = conn.createStatement();
							String query = "SELECT 예약.b_guest_quantity, 예약.b_payment_cost, 메뉴.m_name " +
							               "FROM 예약 INNER JOIN 메뉴 ON 예약.m_number = 메뉴.m_number " +
							               "WHERE 예약.b_number = " + reservationNumber + " AND 예약.b_status = '예약중'";
							ResultSet rs = stmt.executeQuery(query);

							if (rs.next()) {
							    String menuName = rs.getString("m_name"); // Fetch the menu name
							    int guestQuantity = rs.getInt("b_guest_quantity");
							    int totalPrice = rs.getInt("b_payment_cost");

							    content_panel.add(new MyLabel(menuName));
							    content_panel.add(new MyLabel("개수: " + guestQuantity + "개"));
							    content_panel.add(new MyLabel("총 가격 " + totalPrice + "원"));
							}
							panel.add(content_panel);
						}
						{
							JPanel buttonPanel = new JPanel(new BorderLayout());
							buttonPanel.setPreferredSize(new Dimension(80, 25));
							JButton cancelButton = new JButton("예약 취소");
							cancelButton.setMargin(new Insets(0, 0, 0, 0));
							cancelButton.addActionListener(e -> {
								if (YJCafe.showConfirm("정말로 예약을 취소하시겠습니까?")) {
									try {
										Statement stmt2 = conn.createStatement();
										String sql = "UPDATE 회원 SET u_cash = u_cash + (SELECT SUM(b_payment_cost) FROM 예약 WHERE b_number = " + reservationNumber + " AND b_status='예약중') WHERE u_number = " + user.getU_number();
										stmt2.executeUpdate(sql);

										
										sql = "";
										sql += "UPDATE 예약 SET b_status='취소'";
										sql += "WHERE b_number = " + reservationNumber;
										sql += " AND b_status = '예약중'";
										stmt2.executeUpdate(sql);
										
										if (stmt2 != null)
											stmt2.close();
									} catch (Exception err) {
										err.printStackTrace();
									}
								}
							});
							buttonPanel.add(cancelButton, BorderLayout.CENTER);
							panel.add(buttonPanel);
						}
						bottomFrame.add(panel, BorderLayout.NORTH);
					}
					{
						JPanel panels[] = { new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0)),
								new JPanel(new GridLayout(1, 2)) };
						panels[1].setPreferredSize(new Dimension(515, 80));
						{
							JPanel leftPanel = new JPanel(new BorderLayout(30, 0));
							{
								JPanel topicPanel = new JPanel(new GridLayout(3, 1));
								topicPanel.add(new MyLabel("예약 번호", true));
								topicPanel.add(new MyLabel("대표 예약인", true));
								int reservationNumber = exist.getInt("b_number");
	
								JPanel contentPanel = new JPanel(new GridLayout(3, 1));
	
								stmt = conn.createStatement();
								ResultSet rs = stmt.executeQuery(
										"SELECT b_number, b_guest_quantity FROM 예약 WHERE b_number=" + reservationNumber + " AND b_status='예약중'");
								if (rs.next()) {
									contentPanel.add(new MyLabel(String.valueOf(rs.getInt("b_number"))));
									contentPanel.add(new MyLabel(user.getU_name()));
									
								}
	
								leftPanel.add(topicPanel, BorderLayout.WEST);
								leftPanel.add(contentPanel, BorderLayout.CENTER);
							}
							JPanel rightPanel = new JPanel(new BorderLayout(30, 0));
							{
								JPanel topicPanel = new JPanel(new GridLayout(3, 1));
								topicPanel.add(new MyLabel("카페명", true));
								topicPanel.add(new MyLabel("예약 날짜", true));
								topicPanel.add(new MyLabel("예약 시간", true));
	
								JPanel contentPanel = new JPanel(new GridLayout(3, 1));
	
								int reservationNumber = exist.getInt("b_number");
								
								stmt = conn.createStatement();
								String query = "SELECT COUNT(*) FROM 예약_일자 INNER JOIN 예약 ON 예약_일자.b_number = 예약.b_number WHERE 예약.b_number = " + reservationNumber + " AND 예약.b_status = '예약중'";
								ResultSet rs = stmt.executeQuery(query);
								if (rs.next()) {
									int size = rs.getInt(1);
									String query2 = "SELECT 예약_일자.b_date FROM 예약_일자 INNER JOIN 예약 ON 예약_일자.b_number = 예약.b_number WHERE 예약.b_number = " +reservationNumber + " AND 예약.b_status = '예약중' ORDER BY 예약_일자.b_date";
									 rs = stmt.executeQuery(query2);
	
									String b_date = null;
								
									if (rs.next()) {
									    b_date = rs.getString("b_date");
									    // Assuming max_date is to be set as well
									   
									}
			
								     rs = stmt.executeQuery(
										    "SELECT 카페.k_name, 예약_일자.b_hour, 예약_일자.b_minute " +
										    "FROM 예약_일자 " +
										    "INNER JOIN 예약 ON 예약_일자.b_number = 예약.b_number " +
										    "INNER JOIN 카페 ON 예약.k_number = 카페.k_number " +
										    "WHERE 예약.b_number = " + reservationNumber + " AND 예약.b_status = '예약중'"
										);

									if (rs.next()) {
										contentPanel.add(new MyLabel(rs.getString("k_name")));
										contentPanel.add(new MyLabel(b_date));
										contentPanel.add(new MyLabel(rs.getInt("b_hour") + "시"+rs.getInt("b_minute")  +"분"));
									}
								}
	
								rightPanel.add(topicPanel, BorderLayout.WEST);
								rightPanel.add(contentPanel, BorderLayout.CENTER);
							}
							panels[1].add(leftPanel);
							panels[1].add(rightPanel);
						}
						panels[0].add(panels[1]);
						bottomFrame.add(panels[0], BorderLayout.CENTER);
					}
					{
						int reservationNumber = exist.getInt("b_number");
						JPanel panels[] = { new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10)),
								new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)),
								new JPanel(new GridLayout(1, 2, 20, 0)) };
						panels[1].setBorder(
								BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "결제 정보"));
						panels[1].setPreferredSize(new Dimension(PANEL_WIDTH - 30, 75));
						panels[2].setPreferredSize(new Dimension(PANEL_WIDTH - 50, 40));
						{
							JPanel leftPanel = new JPanel(new BorderLayout(20, 0));
							{
								JPanel topicPanel = new JPanel(new GridLayout(2, 1, 0, 10));
								topicPanel.add(new MyLabel("결제 방식", 12, true));
								topicPanel.add(new MyLabel("결제 일자", 12, true));
	
								JPanel contentPanel = new JPanel(new GridLayout(2, 1, 0, 10));
	
								stmt = conn.createStatement();
								ResultSet rs = stmt
										.executeQuery("SELECT b_payment_method, b_payment_date FROM 예약 WHERE b_number=" + reservationNumber
												+ " AND b_status='예약중'");
								if (rs.next()) {
									contentPanel.add(new MyLabel(rs.getString("b_payment_method"), 12));
									contentPanel.add(new MyLabel(rs.getString("b_payment_date"), 12));
								}
	
								leftPanel.add(topicPanel, BorderLayout.WEST);
								leftPanel.add(contentPanel, BorderLayout.CENTER);
							}
							JPanel rightPanel = new JPanel(new BorderLayout(20, 0));
							{
								JPanel topicPanel = new JPanel(new GridLayout(2, 1, 0, 10));
								topicPanel.add(new MyLabel("비용", 12, true));
	
								JPanel contentPanel = new JPanel(new GridLayout(2, 1, 0, 10));
	
								stmt = conn.createStatement();
								ResultSet rs = stmt.executeQuery("SELECT b_payment_cost FROM 예약 WHERE b_number="
										+ reservationNumber + " AND b_status='예약중'");
								if (rs.next())
									contentPanel.add(new MyLabel(String.valueOf(rs.getInt("b_payment_cost")), 12));
	
								rightPanel.add(topicPanel, BorderLayout.WEST);
								rightPanel.add(contentPanel, BorderLayout.CENTER);
							}
							panels[2].add(leftPanel);
							panels[2].add(rightPanel);
							panels[1].add(panels[2]);
						}
						panels[0].add(panels[1]);
						bottomFrame.add(panels[0], BorderLayout.SOUTH);
						
					}
					
				}
				exist.close();
				  stmt.close();
				 if (!hasReservations) { // If no reservations found, show the message
					 JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 100));
				        JLabel label = new JLabel("예약중인 메뉴가 없습니다.");
				        label.setFont(new Font("Monospaced", Font.BOLD, 30));
				        centerPanel.add(label);
				        bottomFrame.add(centerPanel, BorderLayout.CENTER);
				    }
				
			}
			
			{
				JPanel bottomFrame = new JPanel();
				bottomFrame.setLayout(new BoxLayout(bottomFrame, BoxLayout.Y_AXIS));
				JScrollPane scroll = new JScrollPane(bottomFrame);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//
//				tab.addTab("예약 기록", scroll);
//				{
//					Statement stmt = conn.createStatement();
//					ResultSet rs = stmt.executeQuery(
//						    "SELECT 예약.*, 메뉴.m_name " +
//						    "FROM 예약 " +
//						    "INNER JOIN 메뉴 ON 예약.m_number = 메뉴.m_number " +
//						    "WHERE 예약.u_number = " + user.getU_number() + " AND 예약.b_status = '기록'"
//						);
//					while (rs.next()) {
//						JPanel panels[] = { new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)),
//								new JPanel(new GridLayout(1, 2)) };
//						panels[0].setBorder(BorderFactory.createLineBorder(Color.BLACK));
//						{
//							JPanel panel = new JPanel(new BorderLayout(20, 0));
//							{
//								ImagePanel imagePanel = new ImagePanel();
//								imagePanel.setBackground(Color.WHITE);
//								imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//								ResultSet rs2 = conn.createStatement().executeQuery(
//										"SELECT m_image FROM 메뉴_이미지 WHERE m_number=" + rs.getInt("m_number"));
//								if (rs2.next()) {
//									imagePanel.image = ImageIO.read(new URL(rs2.getString("m_image")))
//											.getScaledInstance(100, 100, BufferedImage.SCALE_DEFAULT);
//									imagePanel.repaint();
//								}
//
//								imagePanel.setPreferredSize(new Dimension(100, 100));
//								panel.add(imagePanel, BorderLayout.WEST);
//							}
//							{
//								JPanel inPanel = new JPanel(new BorderLayout(20, 0));
//								{
//									JPanel topicPanel = new JPanel(new GridLayout(3, 1, 0, 5));
//									topicPanel.add(new MyLabel("예약 번호", true));
//									topicPanel.add(new MyLabel("대표 예약인", true));
//									topicPanel.add(new MyLabel("메뉴", true));
//									
//									
//									JPanel contentPanel = new JPanel(new GridLayout(3, 1, 0, 5));
//									contentPanel.add(new MyLabel(String.valueOf(rs.getInt("b_number"))));
//									contentPanel.add(new MyLabel(user.getU_name()));
//									contentPanel.add(new MyLabel(rs.getString("m_name")+" "+rs.getString("b_guest_quantity")+"개"));
//
//									inPanel.add(topicPanel, BorderLayout.WEST);
//									inPanel.add(contentPanel, BorderLayout.CENTER);
//								}
//								panel.add(inPanel, BorderLayout.CENTER);
//							}
//							{
//								JPanel inPanel = new JPanel(new BorderLayout(20, 0));
//								{
//									JPanel topicPanel = new JPanel(new GridLayout(3, 1, 0, 8));
//									topicPanel.add(new MyLabel("카페명", true));
//									topicPanel.add(new MyLabel("예약 날짜", true));
//									topicPanel.add(new MyLabel("예약 시간", true));
//
//									Statement stmt2 = conn.createStatement();
//									ResultSet rs2 = stmt2.executeQuery(
//											"SELECT count(*) FROM 예약_일자 WHERE b_number=" + rs.getInt("b_number"));
//									rs2.next();
//									int size = rs2.getInt(1);
//									rs2 = stmt2.executeQuery("SELECT b_date FROM 예약_일자 WHERE b_number="
//											+ rs.getInt("b_number") + " order by b_date");
//
//									String b_date = null;
//									
//									if (rs2.next()) {
//									    b_date = rs2.getString("b_date");
//									    // Assuming max_date is to be set as well
//									   
//									}
//									rs2 = stmt.executeQuery(
//										    "SELECT 카페.k_name, 예약_일자.b_hour, 예약_일자.b_minute " +
//										    "FROM 예약_일자 " +
//										    "INNER JOIN 예약 ON 예약_일자.b_number = 예약.b_number " +
//										    "INNER JOIN 카페 ON 예약.k_number = 카페.k_number " +
//				
//										    "WHERE 예약.u_number = " + user.getU_number() + " AND 예약.b_status = '취소'"
//										);
//									rs2.next();
//
//									JPanel contentPanel = new JPanel(new GridLayout(3, 1, 0, 8));
//									contentPanel.add(new MyLabel(rs2.getString("k_name")));
//									contentPanel.add(new MyLabel(b_date));
//									contentPanel.add(new MyLabel(rs2.getInt("b_hour") + "시"+rs2.getInt("b_minute")  +"분"));
//
//									inPanel.add(topicPanel, BorderLayout.WEST);
//									inPanel.add(contentPanel, BorderLayout.CENTER);
//								}
//								panel.add(inPanel, BorderLayout.EAST);
//							}
//							panels[1].add(panel);
//						}
//						panels[0].add(panels[1]);
//						bottomFrame.add(panels[0]);
//					}
//				}
			}
			{
				JPanel bottomFrame = new JPanel();
				bottomFrame.setLayout(new BoxLayout(bottomFrame, BoxLayout.Y_AXIS));
				JScrollPane scroll = new JScrollPane(bottomFrame);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

				tab.addTab("취소된 예약", scroll);
				{
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(
							"SELECT 예약.*, 메뉴.m_name, 카페.k_name, 예약_일자.b_hour, 예약_일자.b_minute " +
								    "FROM 예약 " +
								    "INNER JOIN 메뉴 ON 예약.m_number = 메뉴.m_number " +
								    "INNER JOIN 카페 ON 예약.k_number = 카페.k_number " +
								    "INNER JOIN 예약_일자 ON 예약.b_number = 예약_일자.b_number " +
								    "WHERE 예약.u_number = " + user.getU_number() + " AND 예약.b_status = '취소'"
						);
					while (rs.next()) {
						JPanel panels[] = { new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)),
								new JPanel(new GridLayout(1, 2)) };
						panels[0].setBorder(BorderFactory.createLineBorder(Color.BLACK));
						{
							JPanel panel = new JPanel(new BorderLayout(20, 0));
							{
								ImagePanel imagePanel = new ImagePanel();
								imagePanel.setBackground(Color.WHITE);
								imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
								imagePanel.setPreferredSize(new Dimension(100, 100));
								ResultSet rs2 = conn.createStatement().executeQuery(
										"SELECT m_image FROM 메뉴_이미지 WHERE m_number=" + rs.getInt("m_number"));
								if (rs2.next()) {
									imagePanel.image = ImageIO.read(new URL(rs2.getString("m_image")))
											.getScaledInstance(100, 100, BufferedImage.SCALE_DEFAULT);
									imagePanel.repaint();
								}
								panel.add(imagePanel, BorderLayout.WEST);
							}
							{
								JPanel inPanel = new JPanel(new BorderLayout(20, 0));
								{
									JPanel topicPanel = new JPanel(new GridLayout(3, 1, 0, 5));
									topicPanel.add(new MyLabel("예약 번호", true));
									topicPanel.add(new MyLabel("대표 예약인", true));
									topicPanel.add(new MyLabel("메뉴", true));

									JPanel contentPanel = new JPanel(new GridLayout(3, 1, 0, 5));
									contentPanel.add(new MyLabel(String.valueOf(rs.getInt("b_number"))));
									contentPanel.add(new MyLabel(user.getU_name()));
									contentPanel.add(new MyLabel(rs.getString("m_name")+" "+rs.getString("b_guest_quantity")+"개"));

									inPanel.add(topicPanel, BorderLayout.WEST);
									inPanel.add(contentPanel, BorderLayout.CENTER);
								}
								panel.add(inPanel, BorderLayout.CENTER);
							}
							{
								JPanel inPanel = new JPanel(new BorderLayout(20, 0));
								{
									JPanel topicPanel = new JPanel(new GridLayout(3, 1, 0, 8));
									topicPanel.add(new MyLabel("카페명", true));
									topicPanel.add(new MyLabel("예약한 날짜", true));
									topicPanel.add(new MyLabel("예약한 시간", true));

									Statement stmt2 = conn.createStatement();
									ResultSet rs2 = stmt2.executeQuery(
											"SELECT count(*) FROM 예약_일자 WHERE b_number=" + rs.getInt("b_number"));
									rs2.next();
									int size = rs2.getInt(1);
									rs2 = stmt2.executeQuery("SELECT b_date FROM 예약_일자 WHERE b_number="
											+ rs.getInt("b_number") + " order by b_date");

									String b_date = null;
									
									if (rs2.next()) {
									    b_date = rs2.getString("b_date");
									    // Assuming max_date is to be set as well
									   
									}

									ResultSet rs3 = 
											stmt2.executeQuery(
												    "SELECT 예약.*, 메뉴.m_name, 카페.k_name, 예약_일자.b_hour, 예약_일자.b_minute " +
												    "FROM 예약 " +
												    "INNER JOIN 메뉴 ON 예약.m_number = 메뉴.m_number " +
												    "INNER JOIN 카페 ON 예약.k_number = 카페.k_number " +
												    "INNER JOIN 예약_일자 ON 예약.b_number = 예약_일자.b_number " +
												    "WHERE 예약.u_number = " + user.getU_number() + " AND 예약.b_status = '취소'"
										);
									rs3.next();

									JPanel contentPanel = new JPanel(new GridLayout(3, 1, 0, 8));
									contentPanel.add(new MyLabel(rs.getString("k_name")));
									contentPanel.add(new MyLabel(b_date));
									contentPanel.add(new MyLabel(rs.getInt("b_hour") + "시"+rs.getInt("b_minute")  +"분"));

									inPanel.add(topicPanel, BorderLayout.WEST);
									inPanel.add(contentPanel, BorderLayout.CENTER);
								}
								panel.add(inPanel, BorderLayout.EAST);
							}
							panels[1].add(panel);
						}
						panels[0].add(panels[1]);
						bottomFrame.add(panels[0]);
					}
				}
			}
			{
				JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
				{
					JButton button = new JButton("로그아웃");
					button.setPreferredSize(new Dimension(140, 45));
					button.setFont(new Font("굴림", Font.BOLD, 20));
					panel.add(button);
					button.addActionListener(e -> {
						YJCafe.logout();
					});
				}
				add(panel, BorderLayout.CENTER);
			}
			add(tab, BorderLayout.NORTH);
		}
	}
	private class MyLabel extends JLabel {
		private static final long serialVersionUID = 1L;

		public MyLabel(String text) {
			this(text, 15, false);
		}

		public MyLabel(String text, boolean mode) {
			this(text, 15, mode);
		}

		public MyLabel(String text, int size) {
			this(text, size, false);
		}

		public MyLabel(String text, int size, boolean mode) {
			super(text);
			setFont(new Font("굴림", mode ? Font.BOLD : Font.PLAIN, size));
			if (mode)
				setHorizontalAlignment(JLabel.CENTER);
		}
	}
	private class ImagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		Image image;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(image, 0, 0, null);
		}
	}
}