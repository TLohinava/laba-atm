package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.Card;
import com.solvd.atm.persistence.CardRepository;
import com.solvd.atm.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class CardMapperImpl implements CardRepository {

    @Override
    public void create(Long accountId, Card card) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CardRepository cardRepository = sqlSession.getMapper(CardRepository.class);
            cardRepository.create(accountId, card);
        }
    }

    @Override
    public Optional<Card> read(Long id) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CardRepository cardRepository = sqlSession.getMapper(CardRepository.class);
            return cardRepository.read(id);
        }
    }

    @Override
    public void update(Card card) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CardRepository cardRepository = sqlSession.getMapper(CardRepository.class);
            cardRepository.update(card);
        }
    }

    @Override
    public void deleteById(Long deleteId) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CardRepository cardRepository = sqlSession.getMapper(CardRepository.class);
            cardRepository.deleteById(deleteId);
        }
    }
}
