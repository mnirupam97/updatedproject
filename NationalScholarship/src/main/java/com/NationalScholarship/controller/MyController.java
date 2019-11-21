package com.NationalScholarship.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.NationalScholarship.model.Institute;
import com.NationalScholarship.model.ScholarshipRegistrationDocs;
import com.NationalScholarship.model.StudentLoginDetails;
import com.NationalScholarship.model.basicScholarshipRegistration;
import com.NationalScholarship.model.stateNodal;
import com.NationalScholarship.model.student10thDetails;
import com.NationalScholarship.model.student12thDetails;
import com.NationalScholarship.service.ScholarshipRegServiceIntf;






@Controller("myController")
public class MyController {
	
	@Autowired
	ScholarshipRegServiceIntf scholRegServ;

	@RequestMapping(value = "/scholarshipApp", method = RequestMethod.GET)
	  public ModelAndView showRegister2(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("StudAcad10thDetails");
	    mav.addObject("bsr", new basicScholarshipRegistration());
	    return mav;
	  }
	
	@RequestMapping(value = "/scholarshipRegister", method = RequestMethod.POST)
	  public ModelAndView addUser2(HttpServletRequest request, HttpServletResponse response,HttpSession session, @ModelAttribute("basicScholarshipRegistration") basicScholarshipRegistration bsr) {
		StudentLoginDetails sld  = new StudentLoginDetails();
		String student_username=(String) session.getAttribute("student_username");
		sld.setStudent_username(student_username);
		bsr.setStudentLoginDetails(sld);
		//sld.setStatus("N");
		
		
		boolean flag=scholRegServ.register(bsr);
	    if(flag) {
	    ModelAndView mav = new ModelAndView("StudAcad10thDetails");
	    session.setAttribute("student_username",sld.getStudent_username());
	  //  mav.addObject("login", new basicScholarshipRegistration());
	    return mav;
	    }
	    else {
	    	ModelAndView mav = new ModelAndView("register");
	        mav.addObject("user", new basicScholarshipRegistration());
	        mav.addObject("status","Sorry! Registration in incomplete");
	        return mav;	
	    }
	  }
	
	
	@RequestMapping(value ="/Register10th", method = RequestMethod.POST)
	  public ModelAndView addUser10th(HttpServletRequest request, HttpServletResponse response,HttpSession session, @ModelAttribute("student10thDetails") student10thDetails s10d) {
		 
		StudentLoginDetails sld  = new StudentLoginDetails();
		String student_username=(String) session.getAttribute("student_username");
		sld.setStudent_username(student_username);
		s10d.setStudentLoginDetails(sld);
		boolean flag=scholRegServ.register10(s10d);
	    if(flag) {
	    ModelAndView mav = new ModelAndView("student12thDetails");
	   // session.setAttribute("student_id",s10d.getStudent_id());
	  //  mav.addObject("login", new basicScholarshipRegistration());
	    return mav;
	    }
	    else {
	    	ModelAndView mav = new ModelAndView("register");
	        mav.addObject("user", new basicScholarshipRegistration());
	        mav.addObject("status","Sorry! Registration in incomplete");
	        return mav;	
	    }
	  }
	
	@RequestMapping(value = "/Register12th", method = RequestMethod.POST)
	  public ModelAndView addUser12th(HttpServletRequest request, HttpServletResponse response,HttpSession session, @ModelAttribute("student12thDetails") student12thDetails s12d) {
		StudentLoginDetails sld  = new StudentLoginDetails();
		String student_username=(String) session.getAttribute("student_username");
		sld.setStudent_username(student_username);
		s12d.setStudentLoginDetails(sld);
			
		boolean flag=scholRegServ.register12(s12d);
	    if(flag) {
	    ModelAndView mav = new ModelAndView("scholarshipRegistrationDocs");
	   // session.setAttribute("student_id",s10d.getStudent_id());
	  //  mav.addObject("login", new basicScholarshipRegistration());
	    return mav;
	    }
	    else {
	    	ModelAndView mav = new ModelAndView("register");
	        mav.addObject("user", new basicScholarshipRegistration());
	        mav.addObject("status","Sorry! Registration in incomplete");
	        return mav;	
	    }
	  }
/*==========================================================	*/
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("StudentLoginDetails");
	    mav.addObject("sld", new StudentLoginDetails());
	    return mav;
	  } 
	
	
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	  public ModelAndView studentRegister(HttpServletRequest request, HttpServletResponse response,  @ModelAttribute("StudentLoginDetails") StudentLoginDetails sld) 
	 {
		 
		     
			/* String student_name=sld.getStudent_name();
			 String student_gender=sld.getStudent_gender();
			 String student_state_domicile=sld.getStudent_state_domicile();
			 String student_district= sld.getStudent_district();
			 Date student_dob= sld.getStudent_dob();
			 String student_mobile_number= sld.getStudent_mobile_number();
			 String student_email= sld.getStudent_email();
			 String student_institute_code= sld.getStudent_institute_code();
			String student_aadhar_number=sld.getStudent_aadhar_number();
			 String student_password=sld.getStudent_password();
			 
			 */
		Institute institute = new Institute();
		institute.setInst_code(request.getParameter("student_institute_code"));
		sld.setInstitute(institute); 
		sld.setStatus("N");
		
		 
		 boolean flag=scholRegServ.studentRegister(sld);
	    if(flag) {
	    ModelAndView mav = new ModelAndView("scholarshipForm");
	System.out.println("can register ");
	    return mav;
	   
	    
	    }
	    else {
	    	ModelAndView mav = new ModelAndView("error");
	        mav.addObject("user", new StudentLoginDetails());
	        mav.addObject("status","Sorry! Registration in incomplete");
	        return mav;	
	    }
	  }
	/*====================================*/
	@RequestMapping(value = "/institute", method = RequestMethod.GET)
	  public ModelAndView instituteRegister(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("institute");
	    mav.addObject("inst", new Institute());
	    return mav;
	  } 
	
	@RequestMapping(value = "/instituteProcess", method = RequestMethod.POST)
	  public ModelAndView instituteRegister(HttpServletRequest request, HttpServletResponse response,  @ModelAttribute("Institute") Institute inst) 
	 {
				 inst.setInst_approval_status("N");
		 
		 boolean flag=scholRegServ.instituteRegister(inst);
	    if(flag) {
	    ModelAndView mav = new ModelAndView("StudentLoginDetails");
	    System.out.println("can register ");
	    return mav;
	   
	    
	    }
	    else {
	    	ModelAndView mav = new ModelAndView("error");
	        mav.addObject("user", new StudentLoginDetails());
	        mav.addObject("status","Sorry! Registration in incomplete");
	        return mav;	
	    }
	  }
	/*============================================*/
	
	 @RequestMapping(value = "/slogin", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("slogin");
	    mav.addObject("sld", new StudentLoginDetails());
	    return mav;
	  }

	  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,   @ModelAttribute StudentLoginDetails sld) {
	    ModelAndView mav = null;
	    StudentLoginDetails sld1 = scholRegServ.validateUser(sld);
	    if (sld1 != null) {
	      mav = new ModelAndView("scholarshipForm");
	      mav.addObject("firstname", sld1.getStudent_username());
	      //session manage
	      HttpSession session= request.getSession();
	      session.setAttribute("student_username", sld.getStudent_username());
	    } else {
	      mav = new ModelAndView("login");
	      mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }
	  
	 /* =====================================================================*/
	  @RequestMapping(value = "/ilogin", method = RequestMethod.GET)
	  public ModelAndView showLoginInst(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("ilogin");
	    mav.addObject("inst", new Institute());
	    return mav;
	  }
	  
	  @RequestMapping(value = "/iloginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcessInst(HttpSession session,   @ModelAttribute Institute inst) {
	    ModelAndView mav = null;
	    Institute inst1 = scholRegServ.validateUserInst(inst);
	    if (inst1 != null) {
	      mav = new ModelAndView("insthome");
	      mav.addObject("institute_username", inst1.getInst_name());
	      //session manage
	       session.setAttribute("inst_code", inst.getInst_code());
	    } else {
	      mav = new ModelAndView("error");
	      mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }
	  
	  
	  @RequestMapping(value = "/getmyapplicant", method = RequestMethod.GET)
	  public ModelAndView getmyapplicant(HttpSession session) {
		  ModelAndView mav = new ModelAndView("myapplicant");
		    mav.addObject("sld", new StudentLoginDetails());
		    String inst_code =(String)session.getAttribute("inst_code");
		 return mav;
	  }
	  
	  @RequestMapping(value = "/getmyapplicantprocess", method = RequestMethod.GET)
	  public ModelAndView getmyapplicantList(HttpSession session) {
		  //List<basicScholarshipRegistration>  userlist = scholRegServ.getApplications();
		  List<Object[]>  userlist = scholRegServ.getApplications();
			 ModelAndView mav = new ModelAndView("myapplicantlist");
			 mav.addObject("userlist", userlist);
			 return mav;
	  }
	 /* ==================================================================*/
	  
		
		@RequestMapping(value = "/upload", method = RequestMethod.GET)
		  public ModelAndView uploadDoc(HttpServletRequest request, HttpServletResponse response) {
		    ModelAndView mav = new ModelAndView("scholarshipRegistrationDocs");
		    mav.addObject("srd", new ScholarshipRegistrationDocs());
		    return mav;
		  }
		
		@RequestMapping(value = "/uploadProcess", method = RequestMethod.POST)
		  public ModelAndView uploadDoc(HttpServletRequest request, HttpServletResponse response,HttpSession session, @ModelAttribute("ScholarshipRegistrationDocs") ScholarshipRegistrationDocs srd, @RequestParam("file") MultipartFile files[]) {
			
			
			String student_username=(String) session.getAttribute("student_username");
			
			StudentLoginDetails sld  = new StudentLoginDetails();
			sld.setStudent_username(student_username);
			srd.setStudentLoginDetails(sld);

			  String username = student_username;
					for (int i = 0; i < files.length; i++) {
						String filename="";
						if(i==0)
							filename=(username)+"_Domicile"+".pdf";
							else if(i==1)
								filename=(username)+"_Photo"+".pdf";
							else if(i==2)
								filename=(username)+"_IDcard"+".pdf";
							else if(i==3)
								filename=(username)+"_CIcert"+".pdf";
							else if(i==4)
								filename=(username)+"_LatestMarksheet"+".pdf";
							else if(i==5)
								filename=(username)+"_FeeReceipt"+".pdf";
							else if(i==6)
								filename=(username)+"_Passbook"+".pdf";
							else if(i==7)
								filename=(username)+"_Aadhar"+".pdf";
							else if(i==8)
								filename=(username)+"_10thMarksheet"+".pdf";
							else if(i==9)
								filename=(username)+"_12thMarksheet"+".pdf";
						MultipartFile file = files[i];
						try {
							byte[] bytes = file.getBytes();

							// Creating the directory to store file
							String rootPath = System.getProperty("catalina.home");
							File dir = new File(rootPath + File.separator + "tmpFiles");
							if (!dir.exists())
								dir.mkdirs();

							// Create the file on server
							File serverFile = new File(dir.getAbsolutePath()+ File.separator + filename);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();
							
							if(i==0)
		                 srd.setStu_domicile_cert(filename);
							else if(i==1)
								 srd.setStu_photo(filename);
							else if(i==2)
								 srd.setStu_institute_idcard(filename);
							else if(i==3)
								 srd.setStu_caste_income_cert(filename);
							else if(i==4)
								 srd.setStu_previous_year_mark(filename);
							else if(i==5)
								 srd.setStu_fee_receipt_current_year(filename);
							else if(i==6)
								 srd.setStu_bank_passbook(filename);
							else if(i==7)
								 srd.setStu_aadhar_card(filename);
							else if(i==8)
								 srd.setStu_10th_markshhet(filename);
							else if(i==9)
								 srd.setStu_12th_marksheet(filename);
							
							
							System.out.println("Server File Location="+ serverFile.getAbsolutePath());
							} catch (Exception e) {
							System.out.println( "You failed to upload " + (username+i) + " => " + e.getMessage());
						}
					}
				
			boolean flag=scholRegServ.uploadDocuments(srd);
		    if(flag) {
		    ModelAndView mav = new ModelAndView("success");
		   // session.setAttribute("student_id",bsr.getStudent_id());
		  //  mav.addObject("login", new basicScholarshipRegistration());
		    return mav;
		    }
		    else {
		    	ModelAndView mav = new ModelAndView("error");
		        mav.addObject("user", new basicScholarshipRegistration());
		        mav.addObject("status","Sorry! Registration in incomplete");
		        return mav;	
		    }
		  
		
		}}
	
	

