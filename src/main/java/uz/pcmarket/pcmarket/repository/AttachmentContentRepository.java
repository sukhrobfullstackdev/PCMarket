package uz.pcmarket.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pcmarket.pcmarket.entity.Attachment;
import uz.pcmarket.pcmarket.entity.AttachmentContent;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
}
