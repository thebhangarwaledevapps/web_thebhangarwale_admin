package com.app.admin.util.facebook;

import com.app.admin.entity.BhangarwaleFacebookPageDetail;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class BhangarwaleFacebookPageDetailDecoder implements Decoder {
    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        try {
            InputStream inputStream = response.body().asInputStream();
            BufferedReader bR = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuilder responseStrBuilder = new StringBuilder();
            while ((line = bR.readLine()) != null) {
                responseStrBuilder.append(line);
            }
            inputStream.close();
            JSONObject result = new JSONObject(responseStrBuilder.toString());
            String title = result.getString("name");
            String image = result.getJSONObject("picture").getJSONObject("data").getString("url");
            return new BhangarwaleFacebookPageDetail(title, image);
        } catch (Exception e) {
            return null;
        }
    }
}
