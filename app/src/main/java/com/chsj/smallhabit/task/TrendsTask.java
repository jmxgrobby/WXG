package com.chsj.smallhabit.task;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

import android.os.AsyncTask;
import android.util.Log;
import com.chsj.smallhabit.bean.TrendsEntity;
import com.chsj.smallhabit.interfaceses.AfterGetTrends;
import com.chsj.smallhabit.parser.ParserTrends;
import com.chsj.smallhabit.utils.StreamUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 这是一个异步任务类，用来获取网络数据
 */
public class TrendsTask extends AsyncTask<String,Void,String>{

    private AfterGetTrends afterGetTrends;

    public TrendsTask(AfterGetTrends afterGetTrends) {
        this.afterGetTrends = afterGetTrends;
    }

    @Override
    protected String doInBackground(String... params) {
        String ret = null;
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://habit-api.appving.com/Service/Zone.svc/GetZoneMsgs");
        //设置头部信息
//        post.addHeader("Transfer-Encoding","chunked");
        post.addHeader("Content-Type","application/json");
        post.addHeader("Host","habit-api.appving.com");
        post.addHeader("Accept-Encoding","gzip");

        try {

            JSONObject jsonObject = new JSONObject() ;
            jsonObject.put("ReplyPageSize","3");
            jsonObject.put("SysMsgPageSize","20");
            //广场三个ViewPager，中，布局实际上是一样的，只是传的Channel不同
            //0 最新  1 好友 2热门
            jsonObject.put("Channel",params[0]);
            jsonObject.put("PraisePageSize","5");
            jsonObject.put("LastId",params[1]);
            jsonObject.put("ApiKey","7c32efe3adba158b5a675da5ca288bfe");
            jsonObject.put("UserId", params[2]) ;
            jsonObject.put("_elapsed","0") ;

            StringEntity stringEntity = new StringEntity(jsonObject.toString()) ;

            post.setEntity(stringEntity);

            HttpResponse response = client.execute(post);

            int code = response.getStatusLine().getStatusCode();
            if (code == 200){
                // TODO: 2015/11/2 请求成功
                Log.d("TAG", "请求成功：" + code) ;
                InputStream is = null ;
                HttpEntity entity = response.getEntity();
                is = entity.getContent() ;

                byte[] bytes = StreamUtil.readStream(is) ;

                String json = new String(bytes,0,bytes.length) ;

                ret = json;
                Log.d("TAG",json) ;

            }else {
                Log.d("TAG","返回码："+code) ;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    protected void onPostExecute(String s) {
        List<TrendsEntity> list = ParserTrends.getTrendsList(s);
        if (list != null) {
            Log.d("debug111","list长度为"+list.size());
        }else{
            Log.d("debug111","list为空");
        }
        afterGetTrends.sendTrends(list);
    }
}
