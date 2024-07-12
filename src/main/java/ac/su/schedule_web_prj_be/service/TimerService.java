package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.SubjectRecord;
import ac.su.schedule_web_prj_be.repository.SubjectRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TimerService {


    private SubjectRecordRepository subjectRecordRepository;

    public SubjectRecord startTimer(Subject subject) {  // 타이머 시작
        SubjectRecord record = new SubjectRecord();
        record.setSubject(subject);
        record.setRecordedAt(new Date());
        record.setStoppedAt(null);   // 아직 멈추지 않음
        return subjectRecordRepository.save(record);
    }

    public SubjectRecord stopTimer(Long recordId) {  // 타이머 중지
        SubjectRecord record = subjectRecordRepository.findById(recordId).orElseThrow();
        record.setStoppedAt(new Date());
        return subjectRecordRepository.save(record);
    }
    
    public long getElapsedTime(Long recordId) { // 타이머 시간 계산
        SubjectRecord record = subjectRecordRepository.findById(recordId).orElseThrow();
        if (record.getStoppedAt() == null) {
            throw new IllegalStateException("타이머를 정지해주세요");
        }
        return record.getStoppedAt().getTime() - record.getRecordedAt().getTime();
    }
}
