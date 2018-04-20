package imdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import imdb.bindingModel.FilmBindingModel;
import imdb.entity.Film;
import imdb.repository.FilmRepository;

import java.util.List;

@Controller
public class FilmController {

	private final FilmRepository filmRepository;

	@Autowired
	public FilmController(FilmRepository filmRepository) {
		this.filmRepository = filmRepository;
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Film> allFilms= this.filmRepository.findAll();
		if (allFilms == null) {
			return "redirect:/";
		}
		model.addAttribute("view", "film/index");
		model.addAttribute("films", allFilms);
		return "base-layout";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("view", "film/create");
		return "base-layout";

	}

	@PostMapping("/create")
	public String createProcess(Model model, FilmBindingModel filmBindingModel) {

			Film newFilm= new Film();
			newFilm.setName(filmBindingModel.getName());
			newFilm.setDirector(filmBindingModel.getDirector());
			newFilm.setGenre(filmBindingModel.getGenre());
			newFilm.setYear(filmBindingModel.getYear());
			this.filmRepository.saveAndFlush(newFilm);
			System.out.println(" New Project is saved!");
			return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable int id) {
		Film editFilm= filmRepository.findOne(id);
		if (editFilm == null) {
			return "redirect:/";
		}
		model.addAttribute("film", editFilm);
		model.addAttribute("view", "film/edit");
		return "base-layout";
	}
	@PostMapping("/edit/{id}")
	public String editProcess(Model model, @PathVariable int id, FilmBindingModel filmBindingModel) {
		Film editFilm= filmRepository.findOne(id);
		if (editFilm == null) {
			return "redirect:/";
		}
		editFilm.setName(filmBindingModel.getName());
		editFilm.setDirector(filmBindingModel.getDirector());
		editFilm.setGenre(filmBindingModel.getGenre());
		editFilm.setYear(filmBindingModel.getYear());
		this.filmRepository.saveAndFlush(editFilm);
		model.addAttribute("film", editFilm);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id) {
		Film delFilm= filmRepository.findOne(id);

		if (delFilm == null) {
			return "redirect:/";
		}
		model.addAttribute("film", delFilm);
		model.addAttribute("view", "film/delete");
		return "base-layout";
	}

	@PostMapping("/delete/{id}")
	public String deleteProcess(@PathVariable int id) {
		Film delFilm= filmRepository.findOne(id);
		if (delFilm == null) {
			return "redirect:/";
		}
		this.filmRepository.delete(delFilm);
		return "redirect:/";
	}
}
