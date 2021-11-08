package tw.billy.model;

import java.util.Date;
import java.util.List;

import tw.wsm.videoModel.VideoBean;

public class UserService {

	private UserDao userDao = new UserDao();

	// 創建使用者
	public void createUserData(User user) {
		userDao.createUserData(user);
	}

	// 檢查帳號有無重複
	public boolean cheakUsername(String username) {
		return userDao.cheakUsername(username);
	}

	// 檢查email有無重複
	public boolean cheakEmail(String email) {
		return userDao.cheakEmail(email);
	}

	// 使用者登入
	public User loginUser(String username, String password) {
		return userDao.loginUser(username, password);
	}

	// 使用者資料
	public User memberData(String username, String password) {
		return userDao.memberData(username, password);
	}

	// 讀取所有使用者
	public List<User> selectAllMemberData() {
		return userDao.selectAllMemberData();
	}

	// 後臺更新會員資料
	public void updateBackMember(String name, String phone, String email, String address, Integer userId) {
		userDao.updateBackMember(name, phone, email, address, userId);
	}

	// 刪除使用者
	public void deleteMemberById(Integer userId) {
		userDao.deleteMemberById(userId);
	}

	// 更換密碼
	public void updatePassword(String username, String password) {
		userDao.updatePassword(username, password);
	}

	// 更新生日及地址
	public void updateAddress(String username, Date birthday, String address) {
		userDao.updateAddress(username, birthday, address);
	}

	// 更新行業
	public void updateJob(String username, String job) {
		userDao.updateJob(username, job);
	}

	// 更新興趣
	public void updateHabit(String username, String habit) {
		userDao.updateHabit(username, habit);
	}

	// 更新圖片路徑
	public void updateImage(String username, String image) {
		userDao.updateImage(username, image);
	}

	// 獲取會員購買過影片
	public List<VideoBean> getUserVideo(int userId) {
		return userDao.getUserVideo(userId);
	}

	// 確認會員餘額
	public User getUserAmount(int userId) {
		return userDao.getUserAmount(userId);
	}

	// 購買商品扣款
	public void buyVideoUseAmount(Integer videoPrice, Integer userId) {
		userDao.buyVideoUseAmount(videoPrice, userId);
	}

	// 檢查使用者有無此影片
	public boolean checkUserBuyedVideo(Integer userId, Integer videoId) {
		return userDao.checkUserBuyedVideo(userId, videoId);
	}

	// 更新餘額
	public void updateAmount(Integer userid, String sign, int amount) {
		userDao.updateAmount(userid, sign, amount);
	}

	// 獎勵餘額
	public boolean bonusTime(Integer userid) {
		return userDao.bonusTime(userid);
	}

	// 紀錄哪位會員上傳的影片
	public void userUploadVideo(Integer userId, Integer videoId) {
		userDao.userUploadVideo(userId, videoId);
	}

	// 取得哪位會員上傳的影片
	public List<VideoBean> selectUserUploadVideo(Integer userId) {
		return userDao.selectUserUploadVideo(userId);
	}
}
