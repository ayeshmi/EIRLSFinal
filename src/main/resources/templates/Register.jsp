<html xmlns="http://www.w3.org/1999/xhtml" 
    xmlns:th="http://www.thymeleaf.org" 
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
    xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
    
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>k</title> 
</head>
<body>
 <div>
       
    
<div className="form2">
        <Form class="row1"
          onSubmit={this.handleLogin}
          ref={c => {
            this.form = c;
          }}
        >
            <h2 id="headerTitle1">Register</h2>
          
            <label >Username</label>
            <Input
           
            placeholder="Enter your username"
              type="text"
           
              name="username"
              value={this.state.username}
              onChange={this.onChangeUsername}
              validations={[required]}
            />
         

         
            <label htmlFor="password">Email</label>
            <Input
            
            placeholder="Enter your email"
              type="text"
              
              name="email"
              value={this.state.email}
              onChange={this.onChangeEmail}
              validations={[required]}
            />
           

            <label >Birthday</label>
            <Input
           
            placeholder="Enter your birthday"
              type="date"
           
              name="birthday"
              value={this.state.birthday}
              onChange={this.onChangeBirthDay}
              validations={[required]}
            />
            <label >Password</label>
            <Input
           
            placeholder="Enter your password"
              type="password"
           
              name="password"
              value={this.state.password}
              onChange={this.onChangePassword}
              validations={[required]}
            />
             


<p id="capital">
<FontAwesomeIcon  className="fa-times icon" icon={faTimes}/>
<FontAwesomeIcon  className="fa-check icon" icon={faCheck}/>
<span>Capital Letters</span>
</p>
<p id="char">
<FontAwesomeIcon  className="fa-times icon" icon={faTimes}/>
<FontAwesomeIcon  className="fa-check icon" icon={faCheck}/>
<span>Special Characters</span>
</p>
<p id="num">
<FontAwesomeIcon className="fa-times icon" icon={faTimes}/>
<FontAwesomeIcon  className="fa-check icon" icon={faCheck}/>
<span>Use Numbers</span>
</p>
<p id="more8">
<FontAwesomeIcon className="fa-times icon" icon={faTimes}/>
<FontAwesomeIcon  className="fa-check icon" icon={faCheck}/>
<span>8+ Characters</span>
</p>

          <br></br>

          <div className="form-group">
            <button class="row"
              className="btn btn-primary btn-block"
              disabled={this.state.loading}
            >
              {this.state.loading && (
                <span className="spinner-border spinner-border-sm"></span>
              )}
             <span>Regsiter</span>
            </button>
            <br></br>
            <span className='form-input-login'>
        Already have an account? Login <a href='login'>here</a>
      </span>
          </div>
<br></br>

          <CheckButton
            style={{ display: "none" }}
            ref={c => {
              this.checkBtn = c;
            }}
          />
        </Form>
        </div>
       </div>
  );
}
</body>
</html>