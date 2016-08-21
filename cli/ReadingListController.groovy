@Controller
@RequestMapping("/")
class ReadingListController {

    String reader = "Craig"

    @Autowired
    BookRepository bookRepository

    @RequestMapping(method=RequestMethod.GET)
    def readersBooks(Model model) {
        List<Book> readingList =
                bookRepository.findByReader(reader)

        if (readingList != null) {
            model.addAttribute("books", readingList)
        }

        "readingList"
    }

    @RequestMapping(method=RequestMethod.POST)
    def addToReadingList(Book book) {
        book.setReader(reader)
        bookRepository.save(book)
        "redirect:/"
    }

}