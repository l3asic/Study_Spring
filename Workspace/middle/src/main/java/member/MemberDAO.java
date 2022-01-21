package member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository	
public class MemberDAO  {
	
	@Autowired @Qualifier("hanul") private SqlSession sql;
	public void member_insert(MemberVO vo) {
		 sql.insert("member.mapper.insert", vo);
	}
	
	public MemberVO login(MemberVO vo) {
		return sql.selectOne("member.mapper.select", vo);
	}

	


}
