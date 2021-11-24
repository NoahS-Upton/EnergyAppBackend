package com.EnergyForecasting;

import com.EnergyForecasting.Model.Plant;
import com.EnergyForecasting.Repository.PlantRepo;
import com.EnergyForecasting.Service.PlantService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"com.EnergyForecasting"})
@ComponentScan({"com.EnergyForecasting.Model.Calculation"})
@EntityScan("com.EnergyForecasting.*")


public class EnergyForecastingApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(EnergyForecastingApplication.class, args);

		//test code
		PlantRepo pr= new PlantRepo() {
			@Override
			public void deletePlantById(Long id) {

			}

			@Override
			public Optional<Plant> findPlantById(Long id) {
				return Optional.empty();
			}

			@Override
			public List<Plant> findAll() {
				return null;
			}

			@Override
			public List<Plant> findAll(Sort sort) {
				return null;
			}

			@Override
			public List<Plant> findAllById(Iterable<Long> longs) {
				return null;
			}

			@Override
			public <S extends Plant> List<S> saveAll(Iterable<S> entities) {
				return null;
			}

			@Override
			public void flush() {

			}

			@Override
			public <S extends Plant> S saveAndFlush(S entity) {
				return null;
			}

			@Override
			public <S extends Plant> List<S> saveAllAndFlush(Iterable<S> entities) {
				return null;
			}

			@Override
			public void deleteAllInBatch(Iterable<Plant> entities) {

			}

			@Override
			public void deleteAllByIdInBatch(Iterable<Long> longs) {

			}

			@Override
			public void deleteAllInBatch() {

			}

			@Override
			public Plant getOne(Long aLong) {
				return null;
			}

			@Override
			public Plant getById(Long aLong) {
				return null;
			}

			@Override
			public <S extends Plant> List<S> findAll(Example<S> example) {
				return null;
			}

			@Override
			public <S extends Plant> List<S> findAll(Example<S> example, Sort sort) {
				return null;
			}

			@Override
			public Page<Plant> findAll(Pageable pageable) {
				return null;
			}

			@Override
			public <S extends Plant> S save(S entity) {
				return null;
			}

			@Override
			public Optional<Plant> findById(Long aLong) {
				return Optional.empty();
			}

			@Override
			public boolean existsById(Long aLong) {
				return false;
			}

			@Override
			public long count() {
				return 0;
			}

			@Override
			public void deleteById(Long aLong) {

			}

			@Override
			public void delete(Plant entity) {

			}

			@Override
			public void deleteAllById(Iterable<? extends Long> longs) {

			}

			@Override
			public void deleteAll(Iterable<? extends Plant> entities) {

			}

			@Override
			public void deleteAll() {

			}

			@Override
			public <S extends Plant> Optional<S> findOne(Example<S> example) {
				return Optional.empty();
			}

			@Override
			public <S extends Plant> Page<S> findAll(Example<S> example, Pageable pageable) {
				return null;
			}

			@Override
			public <S extends Plant> long count(Example<S> example) {
				return 0;
			}

			@Override
			public <S extends Plant> boolean exists(Example<S> example) {
				return false;
			}
		};
		PlantService  ps= new PlantService(pr);
		ps.getAllPlants();

	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);}
}
