package com.tainy.common.util.constant;

import com.tainy.common.util.vo.CommonContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mogoroom 系统常量类。
 * 
 * @Description
 * @author wangsm
 * @date 2015年6月7日
 */

public class MogoConstant {

	public static final String MOGO_YUNFILE_PATH = "http://image.mogoroom.com"; // 蘑菇云文件服务器（图片路径）

	public static final String MOGO_QIANFANG_SUBMIT_URL = "http://www.1001fang.com/cdapi/submit";

	public static final String MOGO_QIANFANG_QUERY_URL = "http://www.1001fang.com/cdapi/query";

	public static final String MOGO_GET_METHOD = "get";

	public static final String MOGO_POST_METHOD = "post";

	public static final String MOGO_QIANFANG_TOKEN = "b6d9ee32424671d1ea8c8ec36a551ee0";

	public static final String MOGO_QIANFANG_APPID = "a0e37d579c";

	public static final Integer LANDLORD_APP_ROOMSTATUS_PAGE = 15;

	public static final Integer FLATSINFO_PLUG_SHOWITEMS = 15;

	public static ThreadLocal<Integer> currentUser = new ThreadLocal<Integer>();
	public static ThreadLocal<Byte> userType = new ThreadLocal<Byte>();
	public static ThreadLocal<Byte> channel = new ThreadLocal<Byte>();

	public static ThreadLocal<Integer> currentOrgId = new ThreadLocal<Integer>();
	public static ThreadLocal<String> currentRoleIds = new ThreadLocal<String>();

	public static ThreadLocal<String> currentDS = new ThreadLocal<String>();
	public static ThreadLocal<Integer> currentBusiRecordId = new ThreadLocal<Integer>();
	public static ThreadLocal<CommonContext> currentCommonContext = new ThreadLocal<CommonContext>();
	public static ThreadLocal<Integer> currentLandlordId = new ThreadLocal<Integer>();
	public static ThreadLocal<Integer> currentDataScope = new ThreadLocal<Integer>();

	// 销售合同 租期
	public static final Map<Integer, String> SALECONTRACT_LEASETERM = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;

		{
			put(1, "3个月");
			put(2, "6个月");
			put(3, "12个月");
		}
	};

	// 销售合同 类型
	public static final Map<Integer, String> SALECONTRACT_CONTRACTTYPE = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;

		{
			put(1, "蘑菇宝");
			put(2, "非蘑菇宝");
		}
	};

	// 订单状态
	public static final Map<Integer, String> BOOKORDER_STATUS = new HashMap<Integer, String>() {

		private static final long serialVersionUID = 1L;

		{
			put(1, "预定待支付");
			put(2, "预定待审核");
			put(3, "预定成功待签约");
			put(4, "已签约");
			put(5, "预定审核不通过");
			put(6, "房源被他人预定签约");
			put(7, "过期未完成签约");
			put(8, "签约合同到期");
			put(9, "签约被拒绝");
			put(10, "预定已撤销");
			put(11, "预订解除");
		}
	};

	// 预订单状态
	public static final Map<Integer, String> RESERVATIONORDER_STATUS = new HashMap<Integer, String>() {

		private static final long serialVersionUID = 1L;

		{
			put(1, "预约看房");
			put(2, "预约成功");
			put(3, "预定");
			put(4, "已签约");
			put(5, "预约已过期");
		}
	};

	// 银行卡类型
	public static final Map<String, String> BANKCARD_TYPE = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			put("DEBIT", "借记");
			put("CREDIT", "贷记(信用卡)");
		}
	};

	// 本地中转站文件夹
	public static final String FILE_UPLOAD_PATH = "temporaryfile/";
	public static final String STYLE = "style";

	/**
	 * 组织架构
	 */
	public static final int ROOT_BRANCH_PID = 0;// 根部门（蘑菇公寓）的上级部门id，默认为0
	public static final int ROOT_JOB_PID = 0;// 根岗位（即部门直属岗位）的上级岗位id，默认为0
	public static final String ORGXML_TMP_SUFFIX = ".tmp";// xml临时文件后缀
	public static final String ORGXML_BAK_SUFFIX = ".bak";// xml备份文件后缀
	public static final Integer[] SALES_BRANCH_ID_ARRAY = { 53, 54 };// 销售部门上级部门（销售西区和销售东区）的部门id数组

	/**
	 * 预约，预订
	 */
	public static final int ORDER_LAPSED_TIME = 60;// 预订确认时间（分钟）
	public static final int ORDER_SIGN_TIME = 3;// 订单签约有效期（天）
	public static final int RESERVATION_SIGN_TIME = 6;// 预约单快速签约有效时间（小时）

	/**
	 * 批量更新操作
	 *
	 */
	public static final String BATCHUP_CITYS = "citys";// 更新指定城市经纬度
	public static final String BATCHUP_DISTRICT = "district";// 更新指定城市区域经纬度
	public static final String BATCHUP_BUSINESE = "businese";// 更新指定城市商圈经纬度
	public static final String BATCHUP_STATION = "station";// 更新指定城市地铁经纬度
	public static final String BATCHUP_ALLCITYS = "Allcitys";// 更新所有城市经纬度

	/**
	 * 绑卡相关
	 */
	// 银行卡是否绑定状态
	public static final int BANKCARD_USE = 1;// 当前绑定使用状态
	public static final int BANKCARD_NOUSE = 0;// 当前未绑定状态

	// 定时器生成账单分批执行数量
	public static final int SALEBILL_CREATE_COUNT = 100;

	public static final Integer ACCORDING_RATE = 0;
	public static final Integer ACCORDING_AMOUNT = 1;
	public static final Integer PRICE_UP = 0;
	public static final Integer PRICE_DOWN = 1;
	public static final Integer PRICE_TYPE_STANDARD = 1;
	public static final Integer PRICE_TYPE_MOGO = 2;
	public static final Integer CREATE_TYPE_EMP = 0;
	public static final Integer CREATE_TYPE_LANDLORD = 1;

	public static final String MOGO_PAY_KEY = "mogopaykey";

	// 蘑菇租房 房东400电话
	public static final String COMM_PHONE_400 = "400-800-4949";
	public static final String IMAGETYPE_FLATS = "image_flats";
	public static final String IMAGETYPE_ROOM = "image_room";
	public static final String IMAGETYPE_COMMUNITY = "image_community";
	public static final String IMAGETYPE_FLATSTYPE = "img_flat_type";
	public static final String IMAGETYPE_ROOMTYPE = "img_room_type";

	// 房东APP PC channel枚举 无法引用service.base包的 枚举只能写枚举的值
	public static final Integer CHANNELENUM_45 = 45;
	// cache后缀
	public static final String UserLoginToken_PC = "_pc";
	public static final String UserLoginToken_APP = "_app";
	// 登录token前缀
	public static final String USER_LOGIN_PREFIX = "USER-LOGIN-";
	// 代办提醒文案
	public static final String MEMO_TEXT = "当日待办数量：";
	// 支付锁Key前缀
	public static final String PAYLOCK_KEYPRE = "PAYLOCK_";
	// 支付锁-支付定金
	public static final String PAYLOCK_DEPOSIT = "payLock_Deposit";
	// 支付锁-支付账单
	public static final String PAYLOCK_PAYBILL = "payLock_PayBill";
	// 支付锁-支付账单对应的签约单
	public static final String PAYLOCK_PAYBILL_SIGNEDORDER = "payLock_PayBill_SignedOrder";
	// 租客原生App API服务端地址
	public static final String MOGO_RENTERAPP_API_PATH = "https://app.api.mogoroom.com";
	// 身份证正面照
	public static final String CONST_CERTFIRSTIMG = "证件正面";
	// 身份证反面照
	public static final String CONST_CERTSECONDIMG = "证件反面";
	// 手持证件照
	public static final String CONST_NOWPHOTOIMG = "租客和身份证合照";
	// 房东租客合照Key
	public static final String CONST_TWOPARTIESPHOTO = "租客和房东合照";

	// 房东PC首页浏览器tip
	public static final String PC_BROWSER_FILEPATH = "/var/lib/docker/contract/partnerpc_config/browserTip.properties";

	public static final List<String> CONST_MOGOBAOPIC_GROUP = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;

		{
			add(CONST_CERTFIRSTIMG);
			add(CONST_CERTSECONDIMG);
			add(CONST_NOWPHOTOIMG);
			add(CONST_TWOPARTIESPHOTO);
		}
	};

	// 租客房源列表默认图片路径
	public static final String DEFAULT_ROOM_PHOTO_URL = "http://image.mogoroom.com/imagefile/website/index/picturing_list.jpg";

	/**
	 * 用户行为数据收集加密的盐值
	 */
	public static final String CONST_ENCRYPTKEY_COLLECT = "mogoDA_collectUserData";

	/**
	 * 用户行为数据分析加密的盐值
	 */
	public static final String CONST_ENCRYPTKEY_ALYSIS = "mogoDA_analysisUserData";

	/**
	 * 房东pc房态
	 */
	public static final String FANGTAI = "mogopc_fangtai";

	/** 房间详情页的优选品牌图标、精品房源图标、出租方式图标放置upyun的路径 **/
	public static final String roomtagPath = "/common/roomtag/logo/";

	/** 文件类型--图片 **/
	public static final String FILETYPE_IMG = "img";

	/** 文件类型--视频 **/
	public static final String FILETYPE_VIDEO = "video";

}
