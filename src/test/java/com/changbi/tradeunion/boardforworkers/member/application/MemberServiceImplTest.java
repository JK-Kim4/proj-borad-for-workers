package com.changbi.tradeunion.boardforworkers.member.application;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Company;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Department;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Role;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import com.changbi.tradeunion.boardforworkers.member.domain.PreMember;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberDuplicateException;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.MemberDetailDto;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.MemberListDto;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.MemberSaveDto;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.PreMemberDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("loc")
class MemberServiceImplTest {

    @Autowired
    MemberServiceImpl memberService;

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 사용자_엔티티_테스트{


        @Nested
        @DisplayName("사용자 등록 테스트")
        class member_insert_test{

            @BeforeEach
            void setUpSessionAdmin() {
                Long adminId = 10L;
                MockHttpSession session = new MockHttpSession();
                session.setAttribute("admin", memberService.findById(adminId));
                MockHttpServletRequest request = new MockHttpServletRequest();
                request.setSession(session);
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
            }


            @Test @Transactional
            @DisplayName("신규 사용자 등록 성공 시 고유번호를 반환한다.")
            public void new_member_insert_test(){
                //given
                MemberSaveDto dto =  new MemberSaveDto();
                String name = "member1"; String password = "12345";
                Company company = Company.MEDIA_CHANGBI;
                Department department = Department.HR; Role role = Role.USER;
                dto.setMemberEmail(name);
                dto.setMemberPassword(password);
                dto.setCompany(company.name());
                dto.setDepartment(department.name());
                dto.setRole(role.name());

                //when
                Long memberId = memberService.save(dto);
                MemberDetailDto member = memberService.findById(memberId);

                //then
                assertEquals(member.getMemberEmail(), name);
                assertEquals(member.getMemberPassword(), password);
                assertEquals(member.getDepartment(), department.name());
                assertEquals(member.getRole(), role.name());
            }

            @Test @Transactional
            @DisplayName("신규 사용자 등록 시 동일한 이름의 사용자가 있을 경우 [MemberDuplicationException]을 반환한다.")
            public void member_insert_duplication_check(){
                //given
                MemberSaveDto dto =  new MemberSaveDto();
                String name = "member1"; String password = "12345";
                Company company = Company.MEDIA_CHANGBI;
                Department department = Department.HR; Role role = Role.USER;
                dto.setMemberEmail(name);
                dto.setMemberPassword(password);
                dto.setCompany(company.name());
                dto.setDepartment(department.name());
                dto.setRole(role.name());

                //when
                memberService.save(dto);

                //then
                assertThrows(MemberDuplicateException.class, () -> memberService.save(dto));
            }

            @Test @Transactional
            @DisplayName("신규 회원 등록 요청 시 PreMember 객체가 우선 등록된다.")
            public void pre_member_insert_test(){

                //given
                PreMemberDto dto =  new PreMemberDto();
                String email = "member1@changbi.com"; String password = "12345";
                String realName = "tester";
                String company = Company.MEDIA_CHANGBI.name();
                String department = Department.HR.name();
                dto.setMemberEmail(email);
                dto.setMemberPassword(password);
                dto.setMemberRealName(realName);
                dto.setCompany(company);
                dto.setDepartment(department);


                //when
                Long preMemberId = memberService.savePreMember(dto);
                PreMember preMember = memberService.findPreMemberById(preMemberId);

                //then
                assertEquals(email, preMember.getMemberEmail());
                assertEquals(realName, preMember.getMemberRealName());
                //assertEquals(password, preMember.getMemberPassword());
                assertEquals(company, preMember.getCompany().name());
                assertEquals(department, preMember.getDepartment().name());
            }

            @Test @Transactional
            @DisplayName("PreMember 상태의 회원 가입 승인이 완료되면 Member 객체가 등록되고 이전 객체는 삭제된다.")
            public void member_insert_and_pre_member_delete_test(){

                //given
                PreMemberDto dto =  new PreMemberDto();
                String email = "permember@changbi.com"; String password = "12345";
                String realName = "tester";
                String company = Company.MEDIA_CHANGBI.name();
                String department = Department.HR.name();
                dto.setMemberEmail(email);
                dto.setMemberPassword(password);
                dto.setMemberRealName(realName);
                dto.setCompany(company);
                dto.setDepartment(department);

                //when
                Long preMemberId = memberService.savePreMember(dto);
                PreMember preMember = memberService.findPreMemberById(preMemberId);

                Long memberId = memberService.saveMemberByPreMember(preMember);
                MemberDetailDto member = memberService.findById(memberId);


                //then
                assertNull(memberService.findPreMemberById(preMemberId));
                assertEquals(email, member.getMemberEmail());
                assertEquals(realName, member.getMemberRealName());
                //assertEquals(password, member.getMemberPassword());
                assertEquals(company, member.getCompany());
                assertEquals(department, member.getDepartment());
                assertEquals(Role.USER.name(), member.getRole());
            }
        }


        @Nested
        @DisplayName("사용자 엔티티 조회 테스트")
        class member_select_test{

            static int memberSize = 0;

            @BeforeEach
            void insert_member_data(){

                System.out.println("insert member first");
                MemberSaveDto dto1 =  new MemberSaveDto();
                String name = "member1"; String password = "12345";
                Company company = Company.MEDIA_CHANGBI;
                Department department = Department.HR; Role role = Role.USER;
                dto1.setMemberEmail(name);
                dto1.setMemberPassword(password);
                dto1.setCompany(company.name());
                dto1.setDepartment(department.name());
                dto1.setRole(role.name());

                MemberSaveDto dto2 =  new MemberSaveDto();
                String name2 = "member2";
                dto2.setMemberEmail(name2);
                dto2.setMemberPassword(password);
                dto2.setCompany(company.name());
                dto2.setDepartment(department.name());
                dto2.setRole(role.name());

                MemberSaveDto dto3 =  new MemberSaveDto();
                String name3 = "member3";
                dto3.setMemberEmail(name3);
                dto3.setMemberPassword(password);
                dto3.setCompany(company.name());
                dto3.setDepartment(department.name());
                dto3.setRole(role.name());

                memberService.save(dto1);
                memberService.save(dto2);
                memberService.save(dto3);

                memberSize = 8;
            }

            @Test @Transactional
            @DisplayName("등록된 회원 정보를 모두 조회한다.")
            public void member_select_all(){
                //given
                Pagination pagination = memberService.getMemberPagingInfo(0,20);
                //when
                List<MemberListDto> memberList = memberService.findMembers(pagination);
                //then
                //assertEquals(memberSize, memberList.size());
            }

            @Test @Transactional
            @DisplayName("회원 이름(계정)으로 등록된 회원 계정을 조회한다.")
            public void member_select_by_name(){
                //given
                String findMemberName = "member3";
                //when
                Member member = memberService.findByMemberEmail(findMemberName);
                //then
                assertEquals(findMemberName, member.getMemberEmail());
            }
        }
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 사용자_디티오_테스트{

        jakarta.validation.Validator validator;

        @BeforeEach
        void beforeEach(){
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }

        @Test
        @DisplayName("사용자 디티오 필드 Validation 테스트")
        public void member_save_dto_validation(){
            MemberSaveDto dto = new MemberSaveDto();
            dto.setMemberEmail("username1234");

            Set<ConstraintViolation<MemberSaveDto>> constraintViolations = validator.validate(dto);

            assertEquals(constraintViolations.size(), 5);
        }
    }


}