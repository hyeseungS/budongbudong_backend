package com.newjeanssa.budongbudong.controller.news;

import io.jsonwebtoken.io.IOException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@Api(tags = {"뉴스 API"})
public class NewsController {

    @ApiOperation(value = "부동산 뉴스 크롤링", notes = "부동산 뉴스 5개 기사 제목, 내용, 주소 크롤링")
    @GetMapping(value = "")
    public ResponseEntity<?> news() throws IOException {
        List<HashMap<String, String>> newsList = new ArrayList<>();

        String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260";
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
            Elements ele = doc.select("dl");

            for (int i = 0; i < 5; i++) {
                HashMap<String, String> map = new HashMap<>();

                String title = ele.get(i).select("a").text();
                String titleHref = ele.get(i).select("a").attr("href");
                String text = ele.get(i).select("span.lede").text();

                map.put("title", title);
                map.put("titleHref", titleHref);
                map.put("text", text);

                newsList.add(map);
            }

            if (newsList.size() != 0) {
                return new ResponseEntity<List<HashMap<String, String>>>(newsList, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
    }

}