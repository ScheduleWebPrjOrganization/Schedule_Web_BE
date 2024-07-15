//package ac.su.schedule_web_prj_be.service;
//
//import ac.su.schedule_web_prj_be.domain.Member;
//import ac.su.schedule_web_prj_be.domain.StudyGroup;
//import ac.su.schedule_web_prj_be.repository.StudyGroupRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//class StudyGroupServiceTest {
//
//    @Mock
//    private StudyGroupRepository studyGroupRepository;
//
//    @InjectMocks
//    private StudyGroupService studyGroupService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void joinStudyGroup() {
//        // Given
//        Long studyGroupId = 1L;
//        Member newMember = new Member();
//        newMember.setId(2L);
//        newMember.setName("Test Member");
//
//        List<Member> memberList = new ArrayList<>();
//        StudyGroup studyGroup = new StudyGroup();
//        studyGroup.setId(studyGroupId);
//        studyGroup.setMembers(memberList);
//
//        when(studyGroupRepository.findById(studyGroupId)).thenReturn(Optional.of(studyGroup));
//
//        // When
//        studyGroupService.JoinStudyGroup(studyGroupId, newMember);
//
//        // Then
//        verify(studyGroupRepository, times(1)).findById(studyGroupId);
//        verify(studyGroupRepository, times(1)).updateStudyGroupMembersById(studyGroupId, memberList);
//
//        assertTrue(studyGroup.getMembers().contains(newMember));
//    }
//}
