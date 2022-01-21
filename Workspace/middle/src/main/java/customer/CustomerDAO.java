package customer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository	
public class CustomerDAO  {
	
	@Autowired @Qualifier("hanul") private SqlSession sql;
	public void customer_insert(CustomerVO vo) {

	}
	public List<CustomerVO> customer_list(String search) {
		return sql.selectList("customer.mapper.list", search);
	}

	public CustomerVO customer_detail(int id) {
		return sql.selectOne("customer.mapper.detail", id);
	}

	public void customer_update(CustomerVO vo) {
		int a = sql.delete("customer.mapper.update",vo);
		System.out.println(a);
	}

	public void customer_del(int id) {
		sql.delete("customer.mapper.delete",id);
	}

}
