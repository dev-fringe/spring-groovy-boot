package dev.fringe.persistence

import org.apache.ibatis.annotations.Select;

import dev.fringe.model.MainModel

public interface MainMapper {

	@Select('''
			select *
			  from public.MainModel
		''')
	public List<Map> select(MainModel main);
}
