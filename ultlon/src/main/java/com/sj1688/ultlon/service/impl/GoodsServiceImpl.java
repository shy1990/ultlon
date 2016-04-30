package com.sj1688.ultlon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.mysql.GoodsRepository;
import com.sj1688.ultlon.domain.Goods;
import com.sj1688.ultlon.service.GoodsService;


@Service
//@Transactional
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	private GoodsRepository gr;
	

	@Override
	public Goods save(Goods g) {
		return gr.saveAndFlush(g);
	}

	@Override
	public Goods findByBarCode(String barCode) {
		return gr.findByBarCode(barCode);
	}

}
