package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

 import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.Ingredient.Type;
import tacos.Order;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	private TacoRepository  designRepo;
	private IngredientByIdConverter converter;
	
	
	@Autowired
	public  DesignTacoController(IngredientRepository ingredientRepo,
			TacoRepository designRepo,
			IngredientByIdConverter converter) {
		this.ingredientRepo=ingredientRepo;
		this.designRepo=designRepo;
		this.converter=converter;
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		 
//        +++++++Chapter 2+++++++++
		// List<Ingredient> ingredients =Arrays.asList(
//				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//				new Ingredient("COTO", "Corn Tortilla", Type.WRAP), 
//				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), 
//				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//				new Ingredient("CHED", "Cheddar", Type.CHEESE), 
//				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//				new Ingredient("SLSA", "Salsa", Type.SAUCE), 
//				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//				);
		List<Ingredient> ingredients =new ArrayList<>();
		
		//log.info(converter.convert("CARN").toString());
        log.info("in DTC: " + ingredientRepo.findAll());
        
		ingredientRepo.findAll().forEach(i->ingredients.add(i));
				
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		log.info("model :"+ model);
		//model.addAttribute("design", new Taco());
		return "design";

	}
	
	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors,
			@ModelAttribute Order order) {
		log.info("Processing Design :"+ design);
		if(errors.hasErrors()) {
			return "design";
		}
		
		Taco saved=designRepo.save(design);
		log.info("add Taco to order : "+ saved);
		order.addDesign(saved);
		
		log.info("get tacos : "+ order);
		
		
		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}

}
