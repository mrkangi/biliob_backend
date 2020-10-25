package com.jannchie.biliob.controller;

import com.jannchie.biliob.model.VideoInfo;
import com.jannchie.biliob.model.VideoStat;
import com.jannchie.biliob.service.VideoServiceV3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jannchie
 */
@RestController
public class VideoControllerV3 {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    VideoServiceV3 videoService;

    @Autowired
    public VideoControllerV3(VideoServiceV3 videoService) {
        this.videoService = videoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/video/v3/av{aid}/info")
    public VideoInfo getVideoInfo(
            @PathVariable("aid") Long aid) {
        logger.info("获得视频信息[aid: {}]", aid);
        return videoService.getVideoInfo(aid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/video/v3/BV{bvid}/info")
    public VideoInfo getVideoInfo(
            @PathVariable("bvid") String bvid) {
        logger.info("获得视频信息[bvid: {}]", bvid);
        return videoService.getVideoInfo(bvid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/video/v3/av{aid}/stat")
    public List<VideoStat> listVideoStats(
            @PathVariable("aid") Long aid) {
        logger.info("获得视频历史[aid: {}]", aid);
        return videoService.listVideoStat(aid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/video/v3/BV{bvid}/stat")
    public List<VideoStat> listVideoStats(
            @PathVariable("bvid") String bvid) {
        logger.info("获得视频历史[bvid: {}]", bvid);
        return videoService.listVideoStat(bvid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/video/v3/average")
    public Document getAverageByTagData(
            @RequestParam(defaultValue = "-1") Integer tid,
            @RequestParam(defaultValue = "-1") Long pubdate,
            @RequestParam(defaultValue = "-1") Long mid) {
        logger.info("获得平均[tid: {}, mid: {}, pubdate: {}]", tid, mid, pubdate);
        return videoService.getAverage(tid, mid, pubdate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/video/v3/ad")
    public List<VideoInfo> listAd() {
        logger.info("获得广告");
        return videoService.listAd();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/video/v3/search")
    public List<VideoInfo> listSearch(@RequestParam(defaultValue = "") String w,
                                      @RequestParam(defaultValue = "1") Integer p,
                                      @RequestParam(defaultValue = "20") Integer ps,
                                      @RequestParam(defaultValue = "0") Long d,
                                      @RequestParam(defaultValue = "view") String s) {
        logger.info("列出视频[w: {}, p: {}, ps: {}, s: {}, d: {}]", w, p, ps, s, d);
        return videoService.listSearch(w, p, ps, s, d);
    }
}
