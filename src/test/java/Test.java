import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName Test
 * @Description TODO
 * @Author feng
 * @Date 2020/4/3 10:35
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        String data = "{\"code\":200,\"data\":{\"imgUrl\":\"http://44.150.12.19:6551/DownLoadFile?filename=PFSB:/bimg/bimg/data/20200403/11_0/be26601e3de615b82bb9a308f20b7e53\",\"featureInfo\":{\"DEEPLINTXAPIV1\":{\"feature\":\"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\",\"attr\":\"\"}},\"type\":\"FACE\",\"featureImgUrl\":\"http://44.150.12.19:6551/DownLoadFile?filename=PFSB:/bimg/bimg/data/20200403/11_0/63eb5324fdcf3a4428a609f716eaa3d6\"}}";
        JSONObject jsonObject = JSONObject.parseObject(data);
        JSONObject data1 = jsonObject.getJSONObject("data");
        String featureInfo = data1.getJSONObject("featureInfo").toJSONString();

        String s = "http://1233123123:60088/files/Tb_YW_ZTRY";
        s = s.replace("60088", "60077").replace("files", "data/image");
        System.out.println(s);
    }
}
