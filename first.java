package syso;
import java.util.Scanner;
public class first {

	public static void main(String[] args) {
/**
 *数据主题:一组订单信息
 */
	Scanner in = new Scanner(System.in);
	Scanner in1 = new Scanner(System.in);
	String[] names = new String[4];//用餐人
	String[] foods = new String[4];//食品信息
	int [] times = new int[4];//送餐时间
	String[] addresses = new String[4];//送餐地址
	int[] states = new int[4];//订单状态 0 已预定  1  已完成
	double[] sumPrices=new double[4];//总金额
	//初始化两条订单信息
	names[0]="张三";
	foods[0]="红烧带鱼  1份";
	times[0]=12;
	addresses[0]="天成路666号";
	states[0]=0;
	sumPrices[0]=24.0;
	
	names[1]="李四";
	foods[1]="红烧带鱼  2份";
	times[1]=12;
	addresses[1]="天成路666号";
	states[1]=1;
	sumPrices[1]=76.0;	
	
	//数据主题：一组餐品信息
	String[] foodNames= {"红烧带鱼","饺子","烧烤"};
	double[] prices= {38.0,15.0,66.5};
	int[] praiseNums=new int[3];
	
	int num = -1;
	do {
		//循环操作：
		//显示主菜单
		System.out.println("*************************");
		System.out.println("1.我要订餐");
		System.out.println("2.查看餐袋");
		System.out.println("3.签收订单");
		System.out.println("4.删除订单");
		System.out.println("5.我要点赞");
		System.out.println("6.退出系统");
//		提示用户输入编号执行相应功能
		System.out.print("请选择：");
		int choose = in1.nextInt();
		boolean isAdd = false;//true找到了可以插入
		switch(choose) {
		case 1:
			//1.我要订餐
			System.out.println("*****我要订餐*******");
//			1.查找要插入的位置
			for(int i=0;i<names.length;i++) {
				//如果找到为空的位置记录下来
				if(names[i]==null) {
					isAdd=true;//记录找到一个为空的位置
					//执行插入操作
					//a.展示餐品信息
					System.out.println("序号\t餐品名\t单价\t点赞数");
					for(int j=0;j<foodNames.length;j++) {
						String price = prices[j]+"元";
						String praise = praiseNums[j]+"赞";
						System.out.println((j+1)+"\t"+foodNames[j]+"\t"+price+"\t"+praise);
					}
					//输入所选餐品编号以及分数
					System.out.print("请选择所定餐品序号：");
					int chooseDish=in1.nextInt();
					System.out.print("请选择所定份数：");
					int number= in1.nextInt();
					String food = foodNames[chooseDish-1]+"  "+number+"份";
					
//					b.输入订餐人姓名
					System.out.print("请输入订餐人姓名：");
					String name = in.nextLine();
//					c.输入送餐时间
					System.out.print("请输入送餐时间（10-20点整点送餐）");
					int time = in1.nextInt();
//					如果输入错误，重复输入
					while(time<10||time>20) {
						System.out.println("您的输入有误，请输入10-20的整数");
						time=in1.nextInt();
					}
//					d.送餐地址
					System.out.print("请输入送餐地址:");
					String address=in.nextLine();
//					e.计算餐费
					double sumPrice=prices[chooseDish-1]*number;
//					送餐费，当餐费达到50元是  免6元是那个餐费
					double deliCharge = sumPrice>=50?0.0:6.0;
//					显示订单信息
					System.out.println("订餐成功");
					System.out.println("您订的是："+food);
					System.out.println("订餐人："+name);
					System.out.println("送餐时间"+time+"点");
					System.out.println("送餐地址："+address);
					System.out.println("餐费"+sumPrice+",送餐费："+deliCharge+"元");
					System.out.println("总金额："+(sumPrice+deliCharge)+"元");
					
//					保存数据
					names[i]=name;
					foods[i]=food;
					times[i]=time;
					addresses[i]=address;
					sumPrices[i]=sumPrice+deliCharge;
					
					
					
					break;
				}
				
				
				
			}
			if(!isAdd) {
				System.out.println("sorry，您的餐袋已满");
			}
			
			
			//如果没有找到为空的位置 则提示信息
			break;
		case 2:
			System.out.println("******查看餐袋******");
			System.out.println("序号\t订餐人\t所定餐品信息\t送餐时间\t送餐地址\t\t总金额\t状态");
//			遍历数组
			for(int i=0;i<names.length;i++) {
				if(names[i]!=null) {
					String time = times[i]+"点";
					String sumPrice = sumPrices[i]+"元";
					String state = states[i]==0?"已预定":"已完成";
					System.out.println((i+1)+"\t"+names[i]+"\t"+foods[i]+"\t"+time+"\t"+addresses[i]+"\t"+sumPrice+"\t"+state);
				}
				
			}
			
			
			//2.查看餐袋
			break;
		case 3:
			//3.签收订单
			System.out.println("******签收订单******");
			System.out.print("请输入要签收的订单序号：");
			int q = in1.nextInt();
			boolean isFind=false;//记录是否找到这条订单
			for(int i=0;i<names.length;i++) {
				if(names[i]!=null&& states[i]==1&&q==i+1) {
					System.out.println("你的订单已经签收，不能再次签收");
					isFind=true;
					break;
				}else if(names[i]!=null&&states[i]==0&&q==i+1) {
					states[i]=1;//状态修改为已完成状态
					System.out.println("订单签收成功！");
					isFind=true;
					break;
				}
			}
			if(!isFind) {
				System.out.println("对不起，此订单不存在");
			}
			
			break;
		case 4:
			//4.删除订单
//			七、删除订单
//			1. 输入要删除的订单序号(从1开始)
			System.out.println("******删除订单******");
			System.out.print("请输入要删除的订单序号：");
			int delnu=in1.nextInt();
			int delnudex=-1;//删除订单的下标
			boolean isDelFind=false;//记录是否找到
//			2.循环查找这条订单
			for(int i=0;i<names.length;i++) {
//				找到此订单，已签收:执行删除操作
//				找到此订单，且未签收:提示信息未找到此订单:
				if(names[i]!=null&&states[i]==1&&delnu==i+1) {
					delnudex=i;
					System.out.println("删除订单成功");
					isDelFind=true;
					break;
				}else if(names[i]!=null&&states[i]==0&&delnu==i+1) {
					System.out.println("您的订单未签收，不能删除！！！");
					isDelFind=true;
					break;
				}
			}if(!isDelFind) {
				System.out.println("此订单不存在！！！");
			}
			if(delnudex!=-1) {
//				删除操作(循环移位)
//				从要删除的元素后面一个开始，到数组最后一个元素依次向前移动-位
				for(int i=delnudex+1;i<=names.length-1;i++) {
					names[i-1]=names[i];
					foods[i-1]=foods[i];
					times[i-1]=times[i];
					addresses[i-1]=addresses[i];
					states[i-1]=states[i];
					//最后一位清空  I
					names[names.length-1]=null;
					foods[names.length-1]=null;
					times[names.length-1]=0;
					addresses[names.length-1]=null;
					states[names.length-1]=0;
				}
			}
				break;
				
		case 5:
			//5.我要点赞
			System.out.println("******我要点赞******");
			//复制63-68
			System.out.println("序号\t餐品名\t单价\t点赞数");
			for(int j=0;j<foodNames.length;j++) {
				String price = prices[j]+"元";
				String good = praiseNums[j]+"赞";
				System.out.println((j+1)+"\t"+foodNames[j]+"\t"+price+"\t"+good);
			}
			
			System.out.print("请输入您要点赞的商品序号：");
			int nu=in1.nextInt();
			//点赞数加1
			praiseNums[nu-1]++;
//			显示
			System.out.println("点赞成功");
			System.out.println(foodNames[nu-1]+"   "+praiseNums[nu-1]+"赞");
			
			break;
		case 6:
			//6.退出系统
			System.out.println("您已成功退出！！！");
			return;
		
		}
//		提示输入0返回
		System.out.println("输入0返回,其他数字退出");
		num= in1.nextInt();
		
	}while(num==0);
	System.out.println("欢迎使用下次光临");

	}

}
