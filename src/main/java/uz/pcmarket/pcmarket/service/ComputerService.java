package uz.pcmarket.pcmarket.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pcmarket.pcmarket.entity.AttachmentContent;
import uz.pcmarket.pcmarket.entity.Category;
import uz.pcmarket.pcmarket.entity.Computer;
import uz.pcmarket.pcmarket.payload.ComputerDto;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.repository.AttachmentContentRepository;
import uz.pcmarket.pcmarket.repository.CategoryRepository;
import uz.pcmarket.pcmarket.repository.ComputerRepository;

import java.util.Optional;

@Service
public class ComputerService {
    final ComputerRepository computerRepository;
    final AttachmentContentRepository attachmentContentRepository;
    final CategoryRepository categoryRepository;

    public ComputerService(ComputerRepository computerRepository, AttachmentContentRepository attachmentContentRepository, CategoryRepository categoryRepository) {
        this.computerRepository = computerRepository;
        this.attachmentContentRepository = attachmentContentRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Page<Computer>> getComputers(int page, int size) {
        return ResponseEntity.status(HttpStatus.OK).body(computerRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseEntity<Computer> getComputer(Integer id) {
        Optional<Computer> optionalComputer = computerRepository.findById(id);
        return optionalComputer.map(computer -> ResponseEntity.status(HttpStatus.OK).body(computer)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Message> addComputer(ComputerDto computerDto) {
        Optional<AttachmentContent> attachmentContent = attachmentContentRepository.findById(computerDto.getAttachmentId());
        Optional<Category> optionalCategory = categoryRepository.findById(computerDto.getCategoryId());
        if (optionalCategory.isPresent() && attachmentContent.isPresent()) {
            computerRepository.save(new Computer(computerDto.getPrice(), computerDto.getBrand(), computerDto.getColor(), attachmentContent.get(), computerDto.getDescription(),
                    computerDto.getPowerSupply(), computerDto.getMotherboard(), computerDto.getRAM(), computerDto.getCPU(), computerDto.getSSD(), computerDto.getVideoMap(), computerDto.getHDD(), optionalCategory.get()));
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "saved"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The attachment or category was not found!"));
        }
    }


    public ResponseEntity<Message> editComputer(Integer id, ComputerDto computerDto) {
        Optional<AttachmentContent> attachmentContent = attachmentContentRepository.findById(computerDto.getAttachmentId());
        Optional<Category> optionalCategory = categoryRepository.findById(computerDto.getCategoryId());
        Optional<Computer> optionalComputer = computerRepository.findById(id);
        if (optionalComputer.isPresent() && optionalCategory.isPresent() && attachmentContent.isPresent()) {
            Computer computer = optionalComputer.get();
            computer.setCategory(optionalCategory.get());
            computer.setAttachmentContent(attachmentContent.get());
            computer.setPrice(computer.getPrice());
            computer.setBrand(computer.getBrand());
            computer.setColor(computer.getColor());
            computer.setDescription(computer.getDescription());
            computer.setPowerSupply(computer.getPowerSupply());
            computer.setMotherboard(computer.getMotherboard());
            computer.setCPU(computer.getCPU());
            computer.setSSD(computer.getSSD());
            computer.setVideoMap(computer.getVideoMap());
            computer.setHDD(computer.getHDD());
            computerRepository.save(computer);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "Edited!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "Something not found!"));
        }

    }

    public ResponseEntity<Message> deleteComputer(Integer id) {
        Optional<Computer> optionalComputer = computerRepository.findById(id);
        if (optionalComputer.isPresent()) {
            computerRepository.delete(optionalComputer.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "deleted"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "not found"));
        }
    }
}
