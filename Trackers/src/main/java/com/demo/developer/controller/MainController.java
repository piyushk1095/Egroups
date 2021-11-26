package com.demo.developer.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.developer.BugsDto;
import com.demo.developer.model.Bugs;
import com.demo.developer.model.Developer;
import com.demo.developer.model.Stories;
import com.demo.developer.service.BugsService;
import com.demo.developer.service.DeveloperService;
import com.demo.developer.service.StoriesService;

@RestController
public class MainController {

	@Autowired
	private BugsService bugsService;

	@Autowired
	private DeveloperService service;

	@Autowired
	private StoriesService serviceStories;

	// method is used to get List of developers
	@GetMapping("/dev")
	public List<Developer> list1() {

		return service.listAll();
	}

	@GetMapping("/bugs")
	public List<Bugs> listBugs() {
		return bugsService.listAll();
	}

	@PostMapping("/bugs")
	public ResponseEntity<Bugs> add(@RequestBody Bugs bugs) {

		BugsDto bugsDto = new BugsDto();
		if (bugs.getStatus() != null) {
			bugsService.save(bugsDto.statusBugs(bugs));
		}
		return ResponseEntity.ok(bugs);

	}

	@PostMapping("/dev")
	public ResponseEntity<Developer> add(@RequestBody Developer developer) {

		service.save(developer);
		return ResponseEntity.ok(developer);
	}

	@DeleteMapping("/dev/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

	@GetMapping("/stories")
	public List<Stories> list() {

		// System.out.println(stories.getEstimatedPoints());
		return serviceStories.listAll();
	}
	
	
	// to add new story for developer
	@PostMapping("/stories")
	public ResponseEntity<String> add(@RequestBody Stories stories) throws ParseException {

		int weekCount = 10;
		int newWeek = 7;
		int devCount = service.devSet().size();
		Stories st = null;

		if (serviceStories.totalEstimationSet() < devCount * weekCount) {

			stories.setDescription("Week:-1");
			st = storiesAssForFirstTime(stories, weekCount);
		}
		if (st == null) {
			st = stories;
		}
		while (st.getEstimatedPoints() != 0) {

			newWeek = newWeek + 7;
			Stories dateSt = newWeek(st);
			Date date = new Date();

			date = dateSt.getCreationDate();
			weekCount = weekCount + 10;
			Stories stories2 = new Stories();
			stories2.setCreationDate(date);
			stories2.setEstimatedPoints(st.getEstimatedPoints());

			int totalWeek = newWeek / 7;
			String st1 = "week:-" + totalWeek;

			stories2.setDescription(st1);
			stories2.setTitle(st.getTitle());
			if (serviceStories.totalEstimationSet() < (devCount * weekCount)) {
				st = storiesforRestWeek(stories2, weekCount, newWeek);
			}

		}
		return ResponseEntity.ok("successfully added");

	}

	/*
	 * this method is used to validate to check the differance of to dates so that
	 * to get min number of weeks used
	 */
	public int weekValid(Developer developer, Stories stories) {
		Date current = stories.getCreationDate();
		Date maxDate1;
		if (serviceStories.maxDesCreationDate(developer.getName()) != null) {
			maxDate1 = serviceStories.maxDesCreationDate(developer.getName());

		} else {
			maxDate1 = stories.getCreationDate();
		}
		long diffInMillies = Math.abs(maxDate1.getTime() - current.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return (int) diff;

	}

	// check from database to find max date and compare with story date
	public Boolean weekValidFirstTime(Developer developer, Stories stories) {

		Date maxDate1;
		if (serviceStories.maxDesCreationDate(developer.getName()) != null) {
			maxDate1 = serviceStories.maxDesCreationDate(developer.getName());

		} else {
			maxDate1 = stories.getCreationDate();
		}
		long millis = System.currentTimeMillis();
		long diffInMillies = Math.abs(maxDate1.getTime() - millis);
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if (diff <= 7) {
			return false;
		}
		return true;

	}

	// used to add new week to assign
	public Stories newWeek(Stories stories) {// Given Date in String format
		Date dt = stories.getCreationDate();
		int date = stories.getCreationDate().getDate();
		int month = stories.getCreationDate().getMonth();
		if (date + 7 > 30) {
			month = month + 1;

			stories.getCreationDate().setMonth(month);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DAY_OF_MONTH, 7);
		Date newDate = c.getTime();
		stories.getCreationDate().setDate(newDate.getDate());
		return stories;
	}

	// To add stories to developer for first time
	public Stories storiesAssForFirstTime(Stories stories, int weekPoint) {

		weekPoint = 10;
		List<Developer> devList = service.devSet();
		Developer developer = null;
		int estimatedPoint = stories.getEstimatedPoints();
		int diffDate;
		Stories stories2 = new Stories();
		stories2 = stories;

		for (int j = 0; j < devList.size(); j++) {
			developer = new Developer();
			developer = devList.get(j);
			diffDate = weekValid(developer, stories);
			if (diffDate < 7) {

				if (serviceStories.totalEstimation(developer.getName()) == weekPoint - 10) {

					if (estimatedPoint >= weekPoint) {

						int newpointDist = 10;
						stories = new Stories();
						stories.setEstimatedPoints(newpointDist);
						stories.setAssignedDev(developer.getName());
						stories.setCreationDate(stories2.getCreationDate());
						stories.setStatus("Estimated");
						stories.setTitle(stories2.getTitle());
						stories.setDescription(stories2.getDescription());
						estimatedPoint = estimatedPoint - 10;
						serviceStories.save(stories);
					}

					else if (estimatedPoint != 0) {
						stories = new Stories();
						stories.setEstimatedPoints(estimatedPoint);
						stories.setAssignedDev(developer.getName());
						stories.setCreationDate(stories2.getCreationDate());
						stories.setDescription(stories2.getDescription());

						stories.setStatus("Estimated");
						stories.setTitle(stories2.getTitle());

						estimatedPoint = 0;
						serviceStories.save(stories);

					}

				} else if (serviceStories.totalEstimation(developer.getName()) > weekPoint - 10 && estimatedPoint > 0) {

					if (serviceStories.totalEstimation(developer.getName()) + estimatedPoint <= weekPoint
							&& estimatedPoint > 0) {
						stories = new Stories();
						stories.setEstimatedPoints(estimatedPoint);
						stories.setAssignedDev(developer.getName());
						stories.setCreationDate(stories2.getCreationDate());
						stories.setStatus("Estimated");
						stories.setTitle(stories2.getTitle());
						stories.setDescription(stories2.getDescription());

						estimatedPoint = 0;
						serviceStories.save(stories);
					} else if (serviceStories.totalEstimation(developer.getName()) + estimatedPoint >= weekPoint
							&& serviceStories.totalEstimation(developer.getName()) != weekPoint && estimatedPoint > 0) {
						int newEstimatedValue = 10 - serviceStories.totalEstimation(developer.getName());
						stories = new Stories();
						stories.setEstimatedPoints(newEstimatedValue);
						stories.setAssignedDev(developer.getName());
						stories.setCreationDate(stories2.getCreationDate());
						stories.setDescription(stories2.getDescription());

						stories.setStatus("Estimated");
						stories.setTitle(stories2.getTitle());
						estimatedPoint = estimatedPoint - newEstimatedValue;
						serviceStories.save(stories);

					}

				}

			}

		}
		stories2.setEstimatedPoints(estimatedPoint);

		return stories2;

	}

	// To add new stories when developer has already new task in buckets

	public Stories storiesforRestWeek(Stories stories, int weekPoint, int newWeek) {
		List<Developer> devList = service.devSet();
		Developer developer = null;
		int estimatedPoint = stories.getEstimatedPoints();

		Stories stories2 = new Stories();
		stories2 = stories;
		int diffDate;
		for (int j = 0; j < devList.size(); j++) {
			developer = new Developer();
			developer = devList.get(j);

			if (weekValidFirstTime(developer, stories)) {
				diffDate = newWeek - 1;
			} else {
			}

			diffDate = weekValid(developer, stories);
			if (diffDate < newWeek) {
				if (serviceStories.totalEstimation(developer.getName()) == weekPoint - 10) {

					if (estimatedPoint >= 10) {

						int newpointDist = 10;
						stories = new Stories();
						stories.setEstimatedPoints(newpointDist);
						stories.setAssignedDev(developer.getName());
						stories.setCreationDate(stories2.getCreationDate());
						stories.setStatus("Estimated");
						stories.setDescription(stories2.getDescription());
						stories.setTitle(stories2.getTitle());
						estimatedPoint = estimatedPoint - 10;
						serviceStories.save(stories);
					} else if (estimatedPoint != 0) {
						stories = new Stories();
						stories.setEstimatedPoints(estimatedPoint);
						stories.setAssignedDev(developer.getName());
						stories.setCreationDate(stories2.getCreationDate());
						stories.setDescription(stories2.getDescription());
						stories.setStatus("Estimated");
						stories.setTitle(stories2.getTitle());
						estimatedPoint = 0;
						serviceStories.save(stories);
					}

				} else if (serviceStories.totalEstimation(developer.getName()) > weekPoint - 10 && estimatedPoint > 0) {

					if (serviceStories.totalEstimation(developer.getName()) + estimatedPoint <= weekPoint
							&& estimatedPoint > 0) {
						stories = new Stories();
						stories.setEstimatedPoints(estimatedPoint);
						stories.setAssignedDev(developer.getName());
						stories.setCreationDate(stories2.getCreationDate());
						stories.setDescription(stories2.getDescription());
						stories.setStatus("Estimated");
						stories.setTitle(stories2.getTitle());
						estimatedPoint = 0;
						serviceStories.save(stories);
					} else if (serviceStories.totalEstimation(developer.getName()) + estimatedPoint >= weekPoint
							&& serviceStories.totalEstimation(developer.getName()) != weekPoint && estimatedPoint > 0) {
						int newEstimatedValue = weekPoint - serviceStories.totalEstimation(developer.getName());
						stories = new Stories();
						stories.setEstimatedPoints(newEstimatedValue);
						stories.setAssignedDev(developer.getName());
						stories.setCreationDate(stories2.getCreationDate());
						stories.setDescription(stories2.getDescription());
						stories.setStatus("Estimated");
						stories.setTitle(stories2.getTitle());
						estimatedPoint = estimatedPoint - newEstimatedValue;
						serviceStories.save(stories);

					}

				}

			}

		}

		stories2.setEstimatedPoints(estimatedPoint);
		return stories2;

	}

	public MainController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
