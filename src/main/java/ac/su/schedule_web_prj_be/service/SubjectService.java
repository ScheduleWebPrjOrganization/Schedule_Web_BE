package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    // 생성자 주입
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.orElseThrow(() -> new RuntimeException("과목을 찾을 수 없습니다."));
    }

    public Subject updateSubject(Long id, Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject existingSubject = optionalSubject.get();
            existingSubject.setName(subject.getName());
            return subjectRepository.save(existingSubject);
        } else {
            throw new RuntimeException("과목을 찾을 수 없습니다.");
        }
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
