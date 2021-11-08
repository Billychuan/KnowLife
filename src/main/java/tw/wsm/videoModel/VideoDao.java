package tw.wsm.videoModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoDao {
	static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=WebDB";
	static final String USER = "sa";
	static final String PASSWORD = "manager";

	
	// 創建影片
	public void createVideoData(VideoBean videoBean) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "insert into video_data(video_name,video_pic_src,video_vsrc,video_price) values(?,?,?,?)";


		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, videoBean.getVideoName());
			pstmt.setString(2, videoBean.getImgFileName());
			pstmt.setString(3, videoBean.getVideoFileName());
			pstmt.setInt(4, videoBean.getVideoPrice());
			int count = pstmt.executeUpdate();
			System.out.println(count + "筆資料已被新增");

		} catch (SQLException e) {
			System.out.println("錯誤 資料未被新增");
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
		String sql = "select video_ID,video_name,video_pic_src,video_price from video_data";
		List<VideoBean> list = new ArrayList<VideoBean>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
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

					while(rs.next()) {
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
	
	// 更新片名
	public void updateVideoName(String videoName, String videoId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "update [video_data] set [video_name]=? where [video_ID]=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, videoName);
			pstmt.setString(2, videoId);
			pstmt.executeUpdate();
			System.out.println("片名更新成功");

		} catch (SQLException e) {
			System.out.println("發生錯誤 片名更新失敗");
			e.printStackTrace();
		}

	}
	
	// 更新圖片路徑
	public void updateVideoPic(String imgFileName, String videoId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "update [video_data] set [video_pic_src]=? where [video_ID]=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, imgFileName);
			pstmt.setString(2, videoId);
			pstmt.executeUpdate();
			System.out.println("照片更新成功");

		} catch (SQLException e) {
			System.out.println("發生錯誤 照片更新失敗");
			e.printStackTrace();
		}

	}
	
	// 更新影片路徑
	public void updateVideoFileSrc(String videoFileName, String videoId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "update [video_data] set [video_vsrc]=? where [video_ID]=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, videoFileName);
			pstmt.setString(2, videoId);
			pstmt.executeUpdate();
			System.out.println("影片更新成功");

		} catch (SQLException e) {
			System.out.println("發生錯誤 影片更新失敗");
			e.printStackTrace();
		}

	}
	
	// 更新售價
	public void updateVideoPrice(String videoPrice, String videoId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "update [video_data] set [video_price]=? where [video_ID]=?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, videoPrice);
			pstmt.setString(2, videoId);
			pstmt.executeUpdate();
			System.out.println("售價更新成功");

		} catch (SQLException e) {
			System.out.println("發生錯誤 售價更新失敗");
			e.printStackTrace();
		}

	}
	
	// 刪除影片
	public void deleteVideoById(String videoId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "DELETE FROM [dbo].[video_data]\r\n"
				+ "      WHERE [video_ID]=?;";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, videoId);

			int count = pstmt.executeUpdate();
			System.out.println(count + "筆資料已被刪除");

		} catch (SQLException e) {
			System.out.println("錯誤 資料未被刪除");
			e.printStackTrace();
		}
	}
	
	
	
}
