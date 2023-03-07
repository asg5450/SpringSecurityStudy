package me.whiteship.demospringsecurityform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;

@Configuration
//사용하려는 시큐리티 어노테이션을 쓰기 위한 Enable 선언
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
                            //secured 어노테이션     pre Authorized, ...    RolesAllowed어노테이션
public class MethodSecurity extends GlobalMethodSecurityConfiguration {

    //Hierachy 사용을 위한 AccessDicisionManager 커스터마이징 메소드 오버라이딩
    @Override
    protected AccessDecisionManager accessDecisionManager() {
        // 1. Hierachy 규칙 정의
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

        // 2. AffirmativeBased 객체 생성  "부모인 GlobalMethodSecurityConfiguration에서 accessDecisionManager를 받아와서 AffirmativeBased화"
        AffirmativeBased accessDecisionManager = (AffirmativeBased) super.accessDecisionManager();

        // 3. 만들었던 AffirmativeBased 객체에 DecisionVoters에 Hierachy를 매개변수로 RoleHierachVoter 객체 add
        accessDecisionManager.getDecisionVoters().add(new RoleHierarchyVoter(roleHierarchy));

        //Hierachy를 담은 RoleHierachyVoter를 add한 AffirmativeBased를 리턴
        return accessDecisionManager;
    }
}
