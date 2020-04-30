package com.cognizant.moviecruiser.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruiser.dao.MovieDao;
import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.repository.MovieRepository;

@Service
public class MovieService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);
	@Autowired
	private MovieDao movieDao;
	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public List<Movie> getMovieListAdmin() {
		LOGGER.info("start");
		//return movieDao.getMovieListAdmin();
		return movieRepository.findAll();
	}
	
	@Transactional
	public List<Movie> getMovieListCustomer() {
		LOGGER.info("start");
		//return movieDao.getMovieListCustomer();
		return movieRepository.getMovieListCustomer(new Date());
	}
	
	@Transactional
	public void modifyMovie(Movie movie) {
		LOGGER.info("start");
		// movieDao.modifyMovie(movie);
		movieRepository.save(movie);
		LOGGER.info("end");
	}
	
	@Transactional
	public Movie getMovie(long movieId) {
		LOGGER.info("start");
		//return movieDao.getMovie(movieId);
		return movieRepository.getOne(movieId);
	}
}
