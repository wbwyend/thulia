package com.wyer.server;

import com.wyer.server.mapper.*;
import com.wyer.server.model.entity.*;
import com.wyer.server.model.vo.UserCFObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private ShopMapper shopMapper;

	@Autowired
	private SalerMapper salerMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private PurchaseHistoryMapper purchaseHistoryMapper;

	@Autowired
	private BrowseHistoryMapper browseHistoryMapper;

	@Autowired
	private BigDataMapper bigDataMapper;

	@Test
	void contextLoads() {
		String s = "河北\n" +
				"石家庄 唐山 秦皇岛 邯郸 邢台 保定 张家口 承德 沧州 廊坊 衡水\n" +
				"山西\n" +
				"太原 大同 阳泉 长治 晋城 朔州 晋中 运城 忻州 临汾 吕梁\n" +
				"内蒙古\n" +
				"呼和浩特 包头 乌海 赤峰 通辽 鄂尔多斯 呼伦贝尔 巴彦淖尔 乌兰察布\n" +
				"辽宁\n" +
				"沈阳 大连 鞍山 抚顺 本溪 丹东 锦州 营口 阜新 辽阳 盘锦 铁岭 朝阳 葫芦岛\n" +
				"吉林\n" +
				"长春 吉林 四平 辽源 通化 白山 松原 白城\n" +
				"黑龙江\n" +
				"哈尔滨 齐齐哈尔 鸡西 鹤岗 双鸭山 大庆 伊春 佳木斯 七台河 牡丹江 黑河 绥化\n" +
				"江苏\n" +
				"南京 无锡 徐州 常州 苏州 南通 连云港 淮安 盐城 扬州 镇江 泰州 宿迁\n" +
				"浙江\n" +
				"杭州 宁波 温州 嘉兴 湖州 绍兴 金华 衢州 舟山 台州 丽水\n" +
				"安徽\n" +
				"合肥 芜湖 蚌埠 淮南 马鞍山 淮北 铜陵 安庆 黄山 阜阳 宿州 滁州 六安 宣城 池州 亳州\n" +
				"福建\n" +
				"福州 厦门 莆田 三明 泉州 漳州 南平 龙岩 宁德\n" +
				"江西\n" +
				"南昌 景德镇 萍乡 九江 抚州 鹰潭 赣州 吉安 宜春 新余 上饶\n" +
				"山东\n" +
				"济南 青岛 淄博 枣庄 东营 烟台 潍坊 济宁 泰安 威海 日照 临沂 德州 聊城 滨州 菏泽\n" +
				"河南\n" +
				"郑州 开封 洛阳 平顶山 安阳 鹤壁 新乡 焦作 濮阳 许昌 漯河 三门峡 南阳 商丘 信阳 周口 驻马店\n" +
				"湖北\n" +
				"武汉 黄石 十堰 宜昌 襄阳 鄂州 荆门 孝感 荆州 黄冈 咸宁 随州\n" +
				"湖南\n" +
				"长沙 株洲 湘潭 衡阳 邵阳 岳阳 常德 张家界 益阳 郴州 永州 怀化 娄底\n" +
				"广东\n" +
				"广州 韶关 深圳 珠海 汕头 佛山 江门 湛江 茂名 肇庆 惠州 梅州 汕尾 河源 阳江 清远 东莞 中山 潮州 揭阳 云浮\n" +
				"广西\n" +
				"南宁 柳州 桂林 梧州 北海 防城港 钦州 贵港 玉林 百色 贺州 河池 来宾 崇左\n" +
				"海南\n" +
				"海口 三亚 三沙 儋州\n" +
				"四川\n" +
				"成都 自贡 攀枝花 泸州 德阳 绵阳 广元 遂宁 内江 乐山 南充 眉山 宜宾 广安 达州 雅安 巴中 资阳\n" +
				"贵州\n" +
				"贵阳 六盘水 遵义 安顺 毕节 铜仁\n" +
				"云南\n" +
				"昆明 曲靖 玉溪 保山 昭通 丽江 普洱 临沧\n" +
				"西藏\n" +
				"拉萨 日喀则 昌都 林芝 山南 那曲\n" +
				"陕西\n" +
				"西安 铜川 宝鸡 咸阳 渭南 延安 汉中 榆林 安康 商洛\n" +
				"甘肃\n" +
				"兰州 嘉峪关 金昌 白银 天水 武威 张掖 平凉 酒泉 庆阳 定西 陇南\n" +
				"青海\n" +
				"西宁 海东\n" +
				"宁夏\n" +
				"银川 石嘴山 吴忠 固原 中卫\n" +
				"新疆\n" +
				"乌鲁木齐 克拉玛依 吐鲁番 哈密";
		List<String> address = new ArrayList<>();
		address.add("北京");
		address.add("上海");
		address.add("天津");
		address.add("重庆");
		String[] str = s.split("\n");
		for (int i = 0; i < str.length; i += 2) {
			String[] cities = str[i + 1].split(" ");
			int j = 0;
			for (; j < cities.length; j++) {
				String s1 = str[i] + "/"+ cities[j];
				address.add(s1);
			}
		}
		Random random = new Random();

//		for (int i = 5; i <= 30; i++) {
//			StringBuilder sb = new StringBuilder();
//			sb.append(1);
//			for (int j = 0; j < 10; j++) {
//				sb.append(random.nextInt(9));
//			}
//
//			Shop shop = new Shop();
//			shop.setUsername("shop" + i);
//			shop.setPassword("123456");
//			shop.setShopname("shop" + i);
//			shop.setPhone(sb.toString());
//			shop.setEmail(sb + "@sp.com");
//			shop.setAddress(address.get(random.nextInt(address.size())));
//			shop.setAvatar("https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/shop_avatar/default_shop_avatar.jpg");
//
//			shopMapper.insertShop(shop);
//		}

		for (int i = 1; i <= 1000; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(1);
			for (int j = 0; j < 10; j++) {
				sb.append(random.nextInt(9));
			}

			User user = new User();
			user.setUsername("user" + i);
			user.setPassword("123456");
			user.setName("user" + i);
			user.setPhone(sb.toString());
			user.setEmail(sb + "@sp.com");
			user.setAddress(address.get(random.nextInt(address.size())));
			user.setAvatar("https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/user_avatar/default_avatar.jpg");

			userMapper.insertUser(user);
		}




	}


	@Test
	void addSaler() {
		Random rand = new Random();
		int idx = 1;
		for (int i = 1; i <= 30; i++) {
			for (int j = 0; j < 4; j++) {
				Saler saler = new Saler();
				saler.setName("saler" + idx);
				saler.setUsername("saler" + idx);
				saler.setPassword("123456");
				saler.setSid(i);
				saler.setActive(1);
				salerMapper.add(saler);
				idx++;
			}
		}
	}
	@Test
	void updCid() {
		Random rand = new Random();

		for (int i = 1; i <= 600; i++) {
			int cid = 0;
			while (cid == 0) cid = rand.nextInt(36);
			goodsMapper.updateCategory(i, cid);
		}
	}


	@Test
	void addGoods() {
		Random random = new Random();
		int gid = 1, sid = 1, saler = 1;
		while (gid <= 600) {
			Goods goods = new Goods();
			goods.setName("shop" + sid + "的商品" + gid);
			goods.setPrice(BigDecimal.valueOf(random.nextInt(1000)));
			goods.setSales(0);
			goods.setSurplus(random.nextInt(500));
			goods.setPicture("https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/goods_picture/default_goods_picture.jpg");
			goods.setSid(sid);
			goods.setOnsale("true");
			goods.setSaler(saler);
			int cid = 0;
			while (cid == 0) cid = random.nextInt(42);
			goods.setCid(cid);


			gid++;
			if (gid % 20 == 1) sid++;
			if (gid % 5 == 1) saler++;

			goodsMapper.add(goods);
		}
	}

	@Test
	void addHistory() {
		Random random_1 = new Random();
		Random random_2 = new Random(System.currentTimeMillis() % 26384613);
		Random random_3 = new Random(System.currentTimeMillis() % 1515643634);

		long before = 2592000000L; // 1 months before

		for (int i = 0; i < 2000000; i++) {

			if (i % 1000 == 0) System.out.println(i);

			// 1. bh
			int uid = 0;
			while (uid == 0) uid = random_3.nextInt(1001);

			int sid = 0;
			while (sid == 0) sid = random_2.nextInt(31);

			int temp1 = 0;
			while (temp1 == 0) temp1 = random_1.nextInt(21);
			int gid = (sid - 1) * 20 + temp1;



			BrowseHistory browseHistory = new BrowseHistory();
			browseHistory.setUid(uid);
			browseHistory.setSid(sid);
			browseHistory.setGid(gid);



			before -= random_3.nextInt(2592);

			long timestamp = System.currentTimeMillis() - before;
			LocalDateTime currentTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			String formattedDateTime = currentTime.format(formatter);

			browseHistory.setTime(formattedDateTime);

			int seconds = random_2.nextInt(11);
			if (random_1.nextInt(101) <= 35) seconds = seconds * 10 + random_3.nextInt(10);
			if (random_2.nextInt(101) <= 10) seconds = seconds * 10 + random_1.nextInt(10);
			if (random_3.nextInt(101) <= 1) seconds = seconds * 10 + random_2.nextInt(10);

			browseHistory.setSeconds(seconds);

			browseHistoryMapper.add(browseHistory);

			// 2. order & ph
			boolean buy = false;
			if (seconds > 100) {
				if (random_1.nextInt(101) <= 25) buy = true;
			} else if (seconds > 50) {
				if (random_3.nextInt(101) <= 10) buy = true;
			} else if (seconds > 10) {
				if (random_2.nextInt(101) <= 4) buy = true;
			} else if (seconds > 5) {
				if (random_1.nextInt(101) <= 1) buy = true;
			} else {
				if (random_3.nextInt(101) <= 0) buy = true;
			}

			if (!buy) continue;

			Order order = new Order();
			order.setUid(uid);
			order.setOrdername("订单" + System.currentTimeMillis() + uid + random_2.nextInt(10));

			long ordertimestamp = timestamp + seconds * 1000;
			currentTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(ordertimestamp), ZoneId.systemDefault());
			String orderTime = currentTime.format(formatter);

			order.setCreate_time(orderTime);

			long endTimeStamp = ordertimestamp + 10000L + random_1.nextInt(3600000);
			currentTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(endTimeStamp), ZoneId.systemDefault());
			String endTime = currentTime.format(formatter);

			order.setEnd_time(endTime);

			order.setStatus("已完成");

			int number = 1;
			while (random_1.nextInt(11) <= 2 && number < 10) number++;

			BigDecimal price = goodsMapper.selectByGid(gid).getPrice();

			BigDecimal total = price.multiply(BigDecimal.valueOf(number));

			order.setTotal(total);

			orderMapper.insertOrder(order);
			int oid = orderMapper.selectOrdersByUidAndCreateTime(order.getCreate_time(), order.getUid());
			orderMapper.updateOrderCompleteByOid(oid, endTime, "", "已完成");

			PurchaseHistory purchaseHistory = new PurchaseHistory();
			purchaseHistory.setUid(uid);
			purchaseHistory.setSid(sid);
			purchaseHistory.setGid(gid);
			purchaseHistory.setOid(oid);
			purchaseHistory.setNumber(number);
			purchaseHistory.setTotal(total);

			purchaseHistoryMapper.insertPurchaseHistory(purchaseHistory);
			purchaseHistoryMapper.updateTimeByOid(oid, endTime);
		}

	}


	@Test
	void addAddress() {
		String s = "河北\n" +
				"石家庄 唐山 秦皇岛 邯郸 邢台 保定 张家口 承德 沧州 廊坊 衡水\n" +
				"山西\n" +
				"太原 大同 阳泉 长治 晋城 朔州 晋中 运城 忻州 临汾 吕梁\n" +
				"内蒙古\n" +
				"呼和浩特 包头 乌海 赤峰 通辽 鄂尔多斯 呼伦贝尔 巴彦淖尔 乌兰察布\n" +
				"辽宁\n" +
				"沈阳 大连 鞍山 抚顺 本溪 丹东 锦州 营口 阜新 辽阳 盘锦 铁岭 朝阳 葫芦岛\n" +
				"吉林\n" +
				"长春 吉林 四平 辽源 通化 白山 松原 白城\n" +
				"黑龙江\n" +
				"哈尔滨 齐齐哈尔 鸡西 鹤岗 双鸭山 大庆 伊春 佳木斯 七台河 牡丹江 黑河 绥化\n" +
				"江苏\n" +
				"南京 无锡 徐州 常州 苏州 南通 连云港 淮安 盐城 扬州 镇江 泰州 宿迁\n" +
				"浙江\n" +
				"杭州 宁波 温州 嘉兴 湖州 绍兴 金华 衢州 舟山 台州 丽水\n" +
				"安徽\n" +
				"合肥 芜湖 蚌埠 淮南 马鞍山 淮北 铜陵 安庆 黄山 阜阳 宿州 滁州 六安 宣城 池州 亳州\n" +
				"福建\n" +
				"福州 厦门 莆田 三明 泉州 漳州 南平 龙岩 宁德\n" +
				"江西\n" +
				"南昌 景德镇 萍乡 九江 抚州 鹰潭 赣州 吉安 宜春 新余 上饶\n" +
				"山东\n" +
				"济南 青岛 淄博 枣庄 东营 烟台 潍坊 济宁 泰安 威海 日照 临沂 德州 聊城 滨州 菏泽\n" +
				"河南\n" +
				"郑州 开封 洛阳 平顶山 安阳 鹤壁 新乡 焦作 濮阳 许昌 漯河 三门峡 南阳 商丘 信阳 周口 驻马店\n" +
				"湖北\n" +
				"武汉 黄石 十堰 宜昌 襄阳 鄂州 荆门 孝感 荆州 黄冈 咸宁 随州\n" +
				"湖南\n" +
				"长沙 株洲 湘潭 衡阳 邵阳 岳阳 常德 张家界 益阳 郴州 永州 怀化 娄底\n" +
				"广东\n" +
				"广州 韶关 深圳 珠海 汕头 佛山 江门 湛江 茂名 肇庆 惠州 梅州 汕尾 河源 阳江 清远 东莞 中山 潮州 揭阳 云浮\n" +
				"广西\n" +
				"南宁 柳州 桂林 梧州 北海 防城港 钦州 贵港 玉林 百色 贺州 河池 来宾 崇左\n" +
				"海南\n" +
				"海口 三亚 三沙 儋州\n" +
				"四川\n" +
				"成都 自贡 攀枝花 泸州 德阳 绵阳 广元 遂宁 内江 乐山 南充 眉山 宜宾 广安 达州 雅安 巴中 资阳\n" +
				"贵州\n" +
				"贵阳 六盘水 遵义 安顺 毕节 铜仁\n" +
				"云南\n" +
				"昆明 曲靖 玉溪 保山 昭通 丽江 普洱 临沧\n" +
				"西藏\n" +
				"拉萨 日喀则 昌都 林芝 山南 那曲\n" +
				"陕西\n" +
				"西安 铜川 宝鸡 咸阳 渭南 延安 汉中 榆林 安康 商洛\n" +
				"甘肃\n" +
				"兰州 嘉峪关 金昌 白银 天水 武威 张掖 平凉 酒泉 庆阳 定西 陇南\n" +
				"青海\n" +
				"西宁 海东\n" +
				"宁夏\n" +
				"银川 石嘴山 吴忠 固原 中卫\n" +
				"新疆\n" +
				"乌鲁木齐 克拉玛依 吐鲁番 哈密";
		List<String> address = new ArrayList<>();
		address.add("北京");
		address.add("上海");
		address.add("天津");
		address.add("重庆");
		String[] str = s.split("\n");
		for (int i = 0; i < str.length; i += 2) {
			String[] cities = str[i + 1].split(" ");
			int j = 0;
			for (; j < cities.length; j++) {
				String s1 = str[i] + "/"+ cities[j];
				address.add(s1);
			}
		}

		for (String a : address) {
			String[] as = a.split("/");
			String pro = as[0];
			String region = null;
			switch (pro) {
				case "北京":
				case "河北":
				case "山西":
				case "内蒙古":
				case "天津":
					region = "华北";
					break;
				case "黑龙江":
				case "吉林":
				case "辽宁":
					region = "东北";
					break;
				case "陕西":
				case "甘肃":
				case "青海":
				case "宁夏":
				case "新疆":
					region = "西北";
					break;
				case "河南":
				case "湖北":
				case "湖南":
					region = "华中";
					break;
				case "山东":
				case "江苏":
				case "安徽":
				case "上海":
				case "浙江":
				case "江西":
				case "福建":
					region = "华东";
					break;
				case "重庆":
				case "四川":
				case "贵州":
				case "云南":
				case "西藏":
					region = "西南";
					break;
				case "广东":
				case "广西":
				case "海南":
					region = "华南";
					break;
			}
			bigDataMapper.insertAddress(a, pro, region);
		}

	}

	@Autowired
	private TestMapper testMapper;

	@Test
	void userCFTest() {

		// 1.建立矩阵
		List<UserCFObject> browseHistoryData = testMapper.getBrowseHistoryData();
		Map<Integer, Map<Integer, Integer>> browseHistoryDataMap = new HashMap<>();
		for (UserCFObject u : browseHistoryData) {
			if (!browseHistoryDataMap.containsKey(u.getUid())) {
				Map<Integer, Integer> sonMap = new HashMap<>();
				sonMap.put(u.getGid(), u.getScore());
				browseHistoryDataMap.put(u.getUid(), sonMap);
			} else {
				Map<Integer, Integer> sonMap = browseHistoryDataMap.get(u.getUid());
				sonMap.put(u.getGid(), u.getScore());
			}
		}

		List<UserCFObject> purchaseHistoryData = testMapper.getPurChaseHistoryData();
		Map<Integer, Map<Integer, Integer>> purchaseHistoryDataMap = new HashMap<>();
		for (UserCFObject u : purchaseHistoryData) {
			if (!purchaseHistoryDataMap.containsKey(u.getUid())) {
				Map<Integer, Integer> sonMap = new HashMap<>();
				sonMap.put(u.getGid(), u.getScore());
				purchaseHistoryDataMap.put(u.getUid(), sonMap);
			} else {
				Map<Integer, Integer> sonMap = purchaseHistoryDataMap.get(u.getUid());
				sonMap.put(u.getGid(), u.getScore());
			}
		}

		final int userCount = testMapper.getCountUser();
		final int goodsCount = testMapper.getCountGoods();
		final int userId = 1;
		final double browseRatio = 0.3;

		// 2.余弦相似度计算
		double param1 = 0, param2 = 0.0;
		for (int i = 1; i <= goodsCount; i++) {
			double temp1 = browseHistoryDataMap.get(userId).getOrDefault(i, 0);
			double temp2 = purchaseHistoryDataMap.get(userId).getOrDefault(i, 0);
			param1 += temp1 * temp1;
			param2 += temp2 * temp2;
		}
		param1 = Math.sqrt(param1);
		param2 = Math.sqrt(param2);
		PriorityQueue<Map.Entry<Integer, Double>> queue = new PriorityQueue<>(10, (o1, o2) -> o1.getValue() > o2.getValue() ? 1 : -1);
		int size = 10;
		for (int j = 1; j <= userCount; j++) {
			double param5 = 0;
			double param6 = 0;
			double param3 = 0;
			double param4 = 0;
			for (int i = 1; i <= goodsCount; i++) {
				int temp1 = browseHistoryDataMap.get(userId).getOrDefault(i, 0);
				int temp2 = browseHistoryDataMap.get(j).getOrDefault(i, 0);
				int temp3 = purchaseHistoryDataMap.get(userId).getOrDefault(i, 0);
				int temp4 = purchaseHistoryDataMap.get(j).getOrDefault(i, 0);
				param5 += temp1 * temp2;
				param6 += temp3 * temp4;
				param3 += temp2 * temp2;
				param4 += temp4 * temp4;
			}
			param3 = Math.sqrt(param3);
			param4 = Math.sqrt(param4);

			double similarity = param5 / param1 / param3 * browseRatio + param6 / param2 / param4 * (1 - browseRatio);

			// 3.获取相似度最大的用户id
			Map.Entry<Integer, Double> entry = new AbstractMap.SimpleEntry<>(j, similarity);
			if (size-- >= 0) {
				queue.add(entry);
			} else {
				if (!queue.isEmpty() && entry.getValue() > queue.peek().getValue()) {
					queue.poll();
					queue.add(entry);
				}
			}
		}

		List<Integer> uidList = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			if (!queue.isEmpty()) {
				Map.Entry<Integer, Double> entry = queue.poll();
				uidList.add(entry.getKey());
				System.out.println(entry.getKey() + " " + entry.getValue());
			}

		}

		// 4. 推荐商品
		List<Integer> gidList = bigDataMapper.getRecommendGoods(uidList, new Random().nextInt(10));
		for (Integer integer : gidList) {
			System.out.println(integer);
		}


	}



}
