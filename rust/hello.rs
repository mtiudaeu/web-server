use std::fmt;

struct AutokeyKey {
  input_key_code: &'static str,
  key_wait_key_code: &'static str,
  output_key_code: &'static str
}
fn create_autokey_key (
  input_key_code: &'static str,
  key_wait_key_code: &'static str,
  output_key_code: &'static str ) -> AutokeyKey {
  return AutokeyKey{
    input_key_code: input_key_code,
    key_wait_key_code: key_wait_key_code,
    output_key_code: output_key_code
  };
}

struct AutokeyKeyArray(Vec<AutokeyKey>);
fn create_autokey_key_array() -> AutokeyKeyArray {
  return AutokeyKeyArray(Vec::new())
}


//mdtmp debug remove?
impl fmt::Display for AutokeyKey {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        // The `f` value implements the `Write` trait, which is what the
        // write! macro is expecting. Note that this formatting ignores the
        // various flags provided to format strings.
        write!(f, "({}, {}, {})", self.input_key_code, self.key_wait_key_code, self.output_key_code)
    }
}

fn main() {
  let key = create_autokey_key("a", "a", "a");
  let mut key_array = create_autokey_key_array();
//mdtmp debug remove?
  println!("{}", key);

  key_array.0.push(key);


  


}


