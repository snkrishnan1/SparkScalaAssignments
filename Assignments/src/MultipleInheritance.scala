/*



trait Base {
  def op: String
}

trait Class1 extends Base {
  override def op = "foo"
}

trait Class2 extends Base {
  override def op = "bar"
}

class Instance1 extends Class1 with Class2
class Instance2 extends Class2 with Class1

(new Instance1).op
// res0: String = bar

(new Instance2).op
// res1: String = foo


 */