package com.ruoyi.system.poster;
 
import java.awt.Font;
 
import sun.font.FontDesignMetrics;
 
/** 
 * @author tqf
 * @version 创建时间：2020-3-13 下午2:36:41 
 * 类说明:
 */
public class PosterMain {
	public static void main(String[] args) throws Exception {
        String qrCodeUrl = "A:/Users/123/Desktop/图片测试/二维码.jpg"; //二维码
        String goodsUrl = "A:/Users/123/Desktop/图片测试/风景3.jpg"; //顶部图片
        String avatarUrl = "A:/Users/123/Desktop/图片测试/风景3.jpg"; //头像
        String name = "和自己对话";
        String desc = "浔阳江头夜送客，枫叶荻花秋瑟瑟。主人下马客在船，举酒欲饮无管弦。醉不成欢惨将别，别时茫茫江浸月。忽闻水上琵琶声，主人忘归客不发。寻声暗问弹者谁？琵琶声停欲语迟。移船相近邀相见，添酒回灯重开宴。千呼万唤始出来，犹抱琵琶半遮面。转轴拨弦三两声，未成曲调先有情。弦弦掩抑声声思，";
        String price = "重度抑郁1";
        
        Poster poster_ = new Poster();
        poster_.setWidth(2160);
        poster_.setHeight(3480);
        poster_.setQrCodeUrl(qrCodeUrl); //二维码
        poster_.setGoodsUrl(goodsUrl); //顶部banner
        poster_.setDesc(desc); //测评结果说明
        poster_.setPrice(price); //测评结果标题
 
        Poster poster = PosterUtil.initPoster(poster_);
        PosterUtil.drawPoster(poster);
        
        
    }
	
	
	
 
}