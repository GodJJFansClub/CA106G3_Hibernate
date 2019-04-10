package android.com.chef.model;

import java.util.List;

public class ChefService {
	
	private ChefDAO_Interface dao;
	
	public ChefService() {
		dao = new ChefDAO();
	}
	
	public ChefVO addChef(String chef_ID,String chef_area,String chef_resume) {
		
		ChefVO chefVO = new ChefVO();
		chefVO.setChef_ID(chef_ID);
		chefVO.setChef_area(chef_area);
		chefVO.setChef_resume(chef_resume);	
		dao.insert(chefVO);
		
		return chefVO;
	}
	
	public ChefVO updateChef(String chef_status,String chef_area,String chef_channel,String chef_resume) {
		ChefVO chefVO = new ChefVO();
		chefVO.setChef_status(chef_status);
		chefVO.setChef_area(chef_area);
		chefVO.setChef_channel(chef_channel);
		chefVO.setChef_resume(chef_resume);	
		dao.update(chefVO);
		
		return chefVO;
	}
	
	public void deleteChef(String chef_ID) {
		dao.delete(chef_ID);
	}
	public ChefVO getOneChef(String chef_ID) {
		return dao.findByPrimaryKey(chef_ID);
	}
	public List<ChefVO> getAll(){
		return dao.getAll();
	}
	public int selectChefTotal() {
		List<ChefVO> list= getAll();
		return list.size();
	}
}
