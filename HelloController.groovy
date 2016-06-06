@RestController
class HelloController {
	@RequestMapping("/")
	def hello() {
		return "Hello from SpringBoot!"
	}
}
