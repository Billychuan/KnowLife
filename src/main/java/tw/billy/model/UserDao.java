package tw.billy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tw.wsm.videoModel.VideoBean;

public class UserDao {

	static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=WebDB";
	static final String USER = "sa";
	static final String PASSWORD = "manager";

	// 創建使用者
	public void createUserData(User user) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "insert into user_data (user_name, user_gender, user_username, user_password, user_phone, user_email, user_amount, user_createtime) "
				+ "values (?,?,?,?,?,?,?,?)";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getGender());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getEmail());
			pstmt.setInt(7, user.getAmount());
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date current = new Date();
			String date = sdFormat.format(current);
			pstmt.setString(8, date);
			int count = pstmt.executeUpdate();
			System.out.println(count + "筆資料已被新增");

		} catch (SQLException e) {
			System.out.println("錯誤 資料未被新增");
			e.printStackTrace();
		}
	}

	// 檢查資料庫有無此組帳號
	public boolean cheakUsername(String username) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select * from user_data where user_username=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (true == rs.next()) {
				System.out.println("資料庫已有此組帳密");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("可使用此組帳號");
		return true;
	}

	// 檢查資料庫有無此組email
	public boolean cheakEmail(String email) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select * from user_data where user_email=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (true == rs.next()) {
				System.out.println("資料庫已有此組電子信箱");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("可使用此組電子信箱");
		return true;
	}

	// 使用者登入驗證
	public User loginUser(String username, String password) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select * from user_data where user_username=? and user_password=?";
		User user = null;

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (true == rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_ID"));
				user.setName(rs.getString("user_name"));
				user.setUsername(username);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// 讀取使用者資料
	public User memberData(String username, String password) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "select * from user_data where user_username=? and user_password=?";
		User user = null;

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (true == rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_ID"));
				user.setName(rs.getString("user_name"));
				user.setGender(rs.getString("user_gender"));
				user.setUsername(rs.getString("user_username"));
				user.setPassword(rs.getString("user_password"));
				user.setBirthday(rs.getDate("user_birthday"));
				user.setPhone(rs.getString("user_phone"));
				user.setEmail(rs.getString("user_email"));
				user.setAddress(rs.getString("user_address"));
				user.setHabit(rs.getString("user_habit"));
				user.setJob(rs.getString("user_job"));
				user.setAmount(rs.getInt("user_amount"));
				user.setImage(rs.getString("user_image"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// 讀取所有使用者
	public List<User> selectAllMemberData() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		List<User> list = new ArrayList<User>();
		String sql = "select user_ID,user_name,user_gender,user_username,user_birthday,user_phone,user_email,user_address"
				+ " from user_data";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_ID"));
				user.setName(rs.getString("user_name"));
				user.setGender(rs.getString("user_gender"));
				user.setUsername(rs.getString("user_username"));
				user.setBirthday(rs.getDate("user_birthday"));
				user.setPhone(rs.getString("user_phone"));
				user.setEmail(rs.getString("user_email"));
				user.setAddress(rs.getString("user_address"));
				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 刪除使用者
	public void deleteMemberById(Integer userId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "delete user_data where user_ID = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
			System.out.println("會員刪除成功");

		} catch (SQLException e) {
			System.out.println("會員刪除失敗");
			e.printStackTrace();
		}
	}

	// 更新密碼
	public void updatePassword(String username, String password) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_password=? where user_username=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, username);
			int count = pstmt.executeUpdate();
			System.out.println("密碼已被更新");

		} catch (SQLException e) {
			System.out.println("發生錯誤，密碼未更新");
			e.printStackTrace();
		}
	}

	// 更新生日及地址
	public void updateAddress(String username, Date birthday, String address) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_birthday=?, user_address=? where user_username=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, (java.sql.Date) birthday);
			pstmt.setString(2, address);
			pstmt.setString(3, username);
			pstmt.executeUpdate();
			System.out.println("地址與生日更新成功");

		} catch (SQLException e) {
			System.out.println("發生錯誤 地址與生日未更新");
			e.printStackTrace();
		}
	}

	// 更新行業
	public void updateJob(String username, String job) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_job=? where user_username=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, job);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
			System.out.println("行業更新成功");

		} catch (SQLException e) {
			System.out.println("發生錯誤 行業未更新");
			e.printStackTrace();
		}
	}

	// 更新興趣
	public void updateHabit(String username, String habit) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_habit=? where user_username=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, habit);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
			System.out.println("興趣更新成功");

		} catch (SQLException e) {
			System.out.println("發生錯誤 興趣未更新");
			e.printStackTrace();
		}
	}

	// 更新圖片路徑
	public void updateImage(String username, String image) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "update user_data set user_image=? where user_username=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, image);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
			System.out.println("照片更新成功");

		} catch (SQLException e) {
			System.out.println("發生錯誤 照片更新失敗");
			e.printStackTrace();
		}

	}

	// 後臺更新會員資料
	public void updateBackMember(String name, String phone, String email, String address, Integer userId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_name=?, user_phone=?, user_email=?, user_address=? where user_ID=?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, email);
			pstmt.setString(4, address);
			pstmt.setInt(5, userId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 查詢所有影片
	public List<VideoBean> findAllViedo() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select video_ID,video_name,video_pic_src,video_vsrc,video_hit,video_price from video_data";
		List<VideoBean> list = new ArrayList<VideoBean>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				VideoBean videoBean = new VideoBean();
				videoBean.setId(rs.getInt("video_ID"));
				videoBean.setVideoName(rs.getString("video_name"));
				videoBean.setImgFileName(rs.getString("video_pic_src"));
				videoBean.setVideoFileName(rs.getString("video_vsrc"));
				videoBean.setVideoHit(rs.getInt("video_hit"));
				videoBean.setVideoPrice(rs.getInt("video_price"));
				list.add(videoBean);
			}

		} catch (SQLException e) {
			System.out.println("影片查詢失敗");
			e.printStackTrace();
		}
		return list;
	}

	// 模糊搜尋影片
	public List<VideoBean> findSomeViedo(String videoName) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select video_ID,video_name,video_pic_src,video_price from video_data where video_name like ?";
		List<VideoBean> list = new ArrayList<VideoBean>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + videoName + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				VideoBean videoBean = new VideoBean();
				videoBean.setId(rs.getInt("video_ID"));
				videoBean.setVideoName(rs.getString("video_name"));
				videoBean.setImgFileName(rs.getString("video_pic_src"));
				videoBean.setVideoPrice(rs.getInt("video_price"));
				list.add(videoBean);
			}

		} catch (SQLException e) {
			System.out.println("影片查詢失敗");
			e.printStackTrace();
		}
		return list;
	}

	// 取得單一影片
	public List<VideoBean> getCartVideo(ArrayList<VideoBean> cartVideoList) {
		List<VideoBean> videos = new ArrayList<VideoBean>();
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "select * from video_data where video_ID=?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			if (cartVideoList.size() > 0) {
				for (VideoBean item : cartVideoList) {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, item.getId());
					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						VideoBean row = new VideoBean();

						row.setId(rs.getInt("video_ID"));
						row.setVideoName(rs.getString("video_name"));
						row.setImgFileName(rs.getString("video_pic_src"));
						row.setVideoPrice(rs.getInt("video_price"));
						videos.add(row);
					}

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videos;
	}

	// 取得此會員擁有的影片
	public List<VideoBean> getUserVideo(int userId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		List<VideoBean> list = new ArrayList<VideoBean>();
		String sql = "  select vd.video_ID, video_name, video_pic_src, order_date, video_price, video_vsrc, video_hit\r\n" + "  from videoOrder vo \r\n"
				+ "  left join videoOrderDetail vod  on vo.vOrder_ID = vod.vOrder_ID\r\n"
				+ "  left join video_data vd on vod.video_ID = vd.video_ID\r\n" + "  where user_ID = ?;";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				VideoBean vb = new VideoBean();
				vb.setId(rs.getInt("video_ID"));
				vb.setVideoName(rs.getString("video_name"));
				vb.setImgFileName(rs.getString("video_pic_src"));
				vb.setOrderDate(rs.getTimestamp("order_date"));
				vb.setVideoPrice(rs.getInt("video_price"));
				vb.setVideoFileName(rs.getString("video_vsrc"));
				vb.setVideoHit(rs.getInt("video_hit"));
				list.add(vb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 確認餘額
	public User getUserAmount(Integer userId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		User user = null;
		String sql = "select user_amount from user_data where user_ID = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next() == true) {
				user = new User();
				user.setAmount(rs.getInt("user_amount"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// 購買影片後跳扣款
	public void buyVideoUseAmount(Integer videoPrice, Integer userId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_amount = user_amount - ? where user_ID = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, videoPrice);
			pstmt.setInt(2, userId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 檢查使用者有無此影片
	public boolean checkUserBuyedVideo(Integer userId, Integer videoId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		boolean count = false;
		String sql = "select count(*) from videoOrder vo join videoOrderDetail vod on vo.vOrder_ID = vod.vOrder_ID"
				+ " where user_ID = ? AND video_ID = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, videoId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next() == true) {
				int buyed = rs.getInt(1);
				if (buyed >= 1) {
					count = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 代幣扣款
	public void updateAmount(Integer userid, String sign, Integer amount) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "update user_data set user_amount=user_amount - ? where user_ID=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, userid);
			pstmt.executeUpdate();
			System.out.println("餘額更新成功");

		} catch (SQLException e) {
			System.out.println("發生錯誤 餘額更新失敗");
			e.printStackTrace();
		}

	}

	// 代幣獎勵
	public boolean bonusTime(Integer userid) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		Integer time = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT DATEDIFF(SECOND, user_createtime, GetDate()) as time from user_data WHERE user_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// pstmt.setDate(1, (java.sql.Date) date);
			pstmt.setInt(1, userid);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next() == true) {
					time = rs.getInt(1);
					System.out.print(time);
					
				}
			}

			if (time >= 120) {
				String sql2 = "UPDATE user_data SET user_createtime = GetDate() where user_ID = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, userid);
				pstmt2.executeUpdate();

				// int lastTime = Integer.parseInt(sql);

				// System.out.println("時間經過:" + lastTime);

				String sql3 = "UPDATE user_data SET user_amount = user_amount + ? where user_ID = ?";
				// System.out.println("時間經過:" + amount);
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setInt(1, 50);
				pstmt3.setInt(2, userid);
				pstmt3.executeUpdate();
				
				return true;
			} else
				System.out.println("時間還沒到!");
			    
			    return false;

		} catch (SQLException e) {
			System.out.println("發生錯誤");
			e.printStackTrace();
			
			    return false;
		}	
		
	}
	
	//紀錄哪位會員上傳的影片
		public void userUploadVideo(Integer userId, Integer videoId) {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			
			String sql = "insert into userUpload (user_ID, video_ID) values (?,?)";
			try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, userId);
				pstmt.setInt(2, videoId);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//取得哪位會員上傳的影片
		public List<VideoBean> selectUserUploadVideo(Integer userId) {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			List<VideoBean> list = new ArrayList<VideoBean>();
			String sql = "select vd.video_ID,video_name,video_pic_src,video_vsrc,video_hit,video_price from video_data vd join userUpload ud on vd.video_ID = ud.video_ID where user_ID = ? ";
		
			try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, userId);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
				VideoBean videoBean = new VideoBean();
				videoBean.setId(rs.getInt("video_ID"));
				videoBean.setVideoName(rs.getString("video_name"));
				videoBean.setImgFileName(rs.getString("video_pic_src"));
				videoBean.setVideoFileName(rs.getString("video_vsrc"));
				videoBean.setVideoHit(rs.getInt("video_hit"));
				videoBean.setVideoPrice(rs.getInt("video_price"));
				list.add(videoBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}

}
