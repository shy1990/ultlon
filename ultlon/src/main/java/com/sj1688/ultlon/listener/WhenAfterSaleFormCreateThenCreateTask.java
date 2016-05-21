package com.sj1688.ultlon.listener;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.AfterSaleFormCreateEvent;
import com.sj1688.ultlon.service.TaskService;
/**
 * 创建换新单的时候创建售后任务
 * @author 武继明
 *
 */
@Component
public class WhenAfterSaleFormCreateThenCreateTask implements ApplicationListener<AfterSaleFormCreateEvent>{
	private static final Logger LOG=LoggerFactory.getLogger(WhenAfterSaleFormCreateThenCreateTask.class);
	@Autowired
	private TaskService taskService;
	@Autowired
	private B2BDao dao;
	@Override
	public void onApplicationEvent( AfterSaleFormCreateEvent event) {
		try {
			AfterSaleForm afterSaleForm=(AfterSaleForm)event.getSource();
			TaskForm taskForm = new TaskForm();
			taskForm.setAfterForm(afterSaleForm);
			taskService.save(taskForm);
			LOG.info("售后单：{}",afterSaleForm);
			//ctx.publishEvent(new AppMsgEvent(taskForm));//创建app消息通知
			sendApp(afterSaleForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendApp(AfterSaleForm afterSaleForm){
		try {
			//System.out.println("创建了app消息推送");
		//TODO 这里发送app消息通知，等待假斌接口
		String ywPhone = dao.findYewuIdByUsername(afterSaleForm.getUsername()).get(0);
		String url="http://115.28.87.182:28503/v1/push/pushNewAfterSales";
		
		Map<String, String> requet = new HashMap<String,String>();
		requet.put("mobile", ywPhone);
		//requet.put("mobile", "18769727652");
		requet.put("content", "你有一条新售后信息请查看");
	    String msg = JSON.toJSONString(requet);
		
		Document doc = Jsoup.connect(url).header("Content-Type", "Mimetype=application/json").header("Accept", "*/*")
				.data("msg",msg)
				.header("Accept-Encoding", "gzip, deflate, sdch")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
				.ignoreContentType(true).post();
		// System.out.println(doc.body().text());

		//JSONObject jsonObject = JSONObject.parseObject(doc.body().text());
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
}
